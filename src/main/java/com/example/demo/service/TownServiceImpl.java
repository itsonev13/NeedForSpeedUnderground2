package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants;
import com.example.demo.domain.dtos.TownImportDto;
import com.example.demo.domain.entities.Town;
import com.example.demo.repository.TownRepository;
import com.example.demo.util.FileUtil;
import com.example.demo.util.ValidationUtil;
import com.google.gson.Gson;

@Service
public class TownServiceImpl implements TownService {
	private final static String TOWNS_JSON_FILE_PATH = System.getProperty("user.dir")
			+ "//src//main//resources//files//towns.json";
	private TownRepository townRepository;
	private FileUtil fileUtil;
	private final Gson gson;
	private final ValidationUtil validation;
	private final ModelMapper modelMapper;

	@Autowired
	public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validation,
			ModelMapper modelMapper) {
		super();
		this.townRepository = townRepository;
		this.fileUtil = fileUtil;
		this.gson = gson;
		this.validation = validation;
		this.modelMapper = modelMapper;
	}

	@Override
	public Boolean townsAreImported() {

		return this.townRepository.count() > 0;
	}

	@Override
	public String readTownsJsonFile() {
		try {
			return this.fileUtil.readFile(TOWNS_JSON_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String importTowns(String townsFileContent) {
		StringBuilder importResult = new StringBuilder();
		TownImportDto[] towns = gson.fromJson(townsFileContent, TownImportDto[].class);
		Arrays.stream(towns).forEach(townImportDto -> {
			Town townEntity = this.townRepository.findByName(townImportDto.getName()).orElse(null);
			if (townEntity != null) {
				importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
				return;
			}
			if (!(this.validation.isValid(townImportDto))) {
				importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
				return;
			}
			townEntity = this.modelMapper.map(townImportDto, Town.class);
			this.townRepository.saveAndFlush(townEntity);
			importResult.append((String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
					townEntity.getClass().getSimpleName(), townEntity.getName()))).append(System.lineSeparator());
		});
		return importResult.toString().trim();
	}

	@Override
	public String exportRacingTowns() {
		StringBuilder builder = new StringBuilder();
		List<Town> towns = this.townRepository.exportTowns();
		for (Town town : towns) {
			builder.append("Name: " + town.getName() + "\n" + "Racers: " + town.getRacers().size() + "\n");
		}
		return builder.toString();
	}

}
