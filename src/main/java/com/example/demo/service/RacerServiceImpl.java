package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants;
import com.example.demo.domain.dtos.RacerImportDto;
import com.example.demo.domain.dtos.TownImportDto;
import com.example.demo.domain.entities.Car;
import com.example.demo.domain.entities.Racer;
import com.example.demo.domain.entities.Town;
import com.example.demo.repository.RacerRepository;
import com.example.demo.repository.TownRepository;
import com.example.demo.util.FileUtil;
import com.example.demo.util.ValidationUtil;
import com.google.gson.Gson;

@Service
public class RacerServiceImpl implements RacerService {
	private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir")
			+ "//src//main//resources//files//racers.json";
	private RacerRepository racerRepository;
	private final TownRepository townRepository;
	private FileUtil fileUtil;
	private final Gson gson;
	private final ValidationUtil validation;
	private final ModelMapper modelMapper;

	@Autowired
	public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtil,
			Gson gson, ValidationUtil validation, ModelMapper modelMapper) {
		super();
		this.racerRepository = racerRepository;
		this.townRepository = townRepository;
		this.fileUtil = fileUtil;
		this.gson = gson;
		this.validation = validation;
		this.modelMapper = modelMapper;
	}

	@Override
	public Boolean racersAreImported() {
		return this.racerRepository.count() > 0;
	}

	@Override
	public String readRacersJsonFile() {
		try {
			return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String importRacers(String racersFileContent) {
		StringBuilder importResult = new StringBuilder();
		RacerImportDto[] racers = gson.fromJson(racersFileContent, RacerImportDto[].class);
		Arrays.stream(racers).forEach(racerImportDto -> {
			Racer racerEntity = this.racerRepository.findByName(racerImportDto.getName()).orElse(null);
			Town townEntity = this.townRepository.findByName(racerImportDto.getHomeTown()).orElse(null);
			if (racerEntity != null) {
				importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
				return;
			}
			if (!(this.validation.isValid(racerImportDto))) {
				importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
				return;
			}
			if (townEntity == null) {
				TownImportDto townImportDto = new TownImportDto();
				townImportDto.setName(racerImportDto.getHomeTown());
				townEntity = this.modelMapper.map(townImportDto, Town.class);
				this.townRepository.saveAndFlush(townEntity);
			}

			racerEntity = this.modelMapper.map(racerImportDto, Racer.class);
			racerEntity.setHometown(townEntity);
			this.racerRepository.saveAndFlush(racerEntity);
			importResult
					.append((String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
							racerRepository.getClass().getSimpleName(), racerEntity.getName())))
					.append(System.lineSeparator());
		});
		return importResult.toString().trim();
	}

	@Override
	public String exportRacingCars() {
		StringBuilder builder = new StringBuilder();
		List<Racer> racers = this.racerRepository.findAll();
		for (Racer r : racers) {
			builder.append("Name: " + r.getName() + "\n" + "Cars:\n");
			Set<Car> cars = r.getCars();
			for (Car c : cars) {
				builder.append(c.getBrand() + " " + c.getModel() + " " + c.getYearOfProduction() + "\n");
			}
		}
		System.out.println("hi");
		System.out.println(gson.toJson(racers));

		return builder.toString();
	}
}
