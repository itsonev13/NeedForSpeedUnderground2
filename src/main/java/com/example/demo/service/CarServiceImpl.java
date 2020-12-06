package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants;
import com.example.demo.domain.dtos.CarsImportDto;
import com.example.demo.domain.dtos.RacerImportDto;
import com.example.demo.domain.entities.Car;
import com.example.demo.domain.entities.Racer;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.RacerRepository;
import com.example.demo.util.FileUtil;
import com.example.demo.util.ValidationUtil;
import com.google.gson.Gson;

@Service
public class CarServiceImpl implements CarService {

	private final static String CARS_JSON_FILE_PATH = System.getProperty("user.dir")
			+ "//src//main//resources//files//cars.json";
	private CarRepository carRepository;
	private RacerRepository racerRepository;
	private FileUtil fileUtil;
	private final Gson gson;
	private final ValidationUtil validation;
	private final ModelMapper modelMapper;

	@Autowired
	public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, Gson gson,
			ValidationUtil validation, ModelMapper modelMapper) {
		super();
		this.carRepository = carRepository;
		this.racerRepository = racerRepository;
		this.fileUtil = fileUtil;
		this.gson = gson;
		this.validation = validation;
		this.modelMapper = modelMapper;
	}

	@Override
	public Boolean carsAreImported() {
		return this.carRepository.count() > 0;
	}

	@Override
	public String readCarsJsonFile() throws IOException {
		try {
			return this.fileUtil.readFile(CARS_JSON_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String importCars(String carsFileContent) {
		StringBuilder importResult = new StringBuilder();
		CarsImportDto[] cars = gson.fromJson(carsFileContent, CarsImportDto[].class);
		Arrays.stream(cars).forEach(carImportDto -> {
			Car carEntity = this.carRepository.findByBrand(carImportDto.getBrand()).orElse(null);
			Racer racerEntity = this.racerRepository.findByName(carImportDto.getRacerName()).orElse(null);
			if (carEntity != null) {
				importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
				return;
			}
			if (!(this.validation.isValid(carImportDto))) {
				importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
				return;
			}
			if (racerEntity == null) {
				RacerImportDto racerImportDto = new RacerImportDto();
				racerImportDto.setName(carImportDto.getRacerName());
				racerEntity = this.modelMapper.map(racerImportDto, Racer.class);
				this.racerRepository.saveAndFlush(racerEntity);
			}

			carEntity = this.modelMapper.map(carImportDto, Car.class);
			carEntity.setRacer(racerEntity);
			this.carRepository.saveAndFlush(carEntity);
			importResult
					.append((String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
							carRepository.getClass().getSimpleName(), carEntity.getBrand())))
					.append(System.lineSeparator());
		});
		return importResult.toString().trim();
	}

}
