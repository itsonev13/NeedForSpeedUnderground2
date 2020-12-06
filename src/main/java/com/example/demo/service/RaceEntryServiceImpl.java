package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants;
import com.example.demo.domain.dtos.RaceEntriesXmlDtos;
import com.example.demo.domain.dtos.RaceEntryXmlDto;
import com.example.demo.domain.entities.Car;
import com.example.demo.domain.entities.RaceEntry;
import com.example.demo.domain.entities.Racer;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.RaceEntryRepository;
import com.example.demo.repository.RacerRepository;
import com.example.demo.util.FileUtil;
import com.example.demo.util.ValidationUtil;
import com.example.demo.util.XmlParser;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

	private final static String RACES_Enties_JSON_FILE_PATH = System.getProperty("user.dir")
			+ "//src//main//resources//files//race-entries.xml";
	private RaceEntryRepository raceEntryRepository;
	private RacerRepository racerRepository;
	private CarRepository carRepository;
	private FileUtil fileUtil;
	private final XmlParser xmlparser;
	private final ValidationUtil validation;
	private final ModelMapper modelMapper;

	@Autowired
	public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, FileUtil fileUtil, XmlParser xmlparser,
			ValidationUtil validation, ModelMapper modelMapper, CarRepository carRepository,
			RacerRepository racerRepository) {
		super();
		this.raceEntryRepository = raceEntryRepository;
		this.fileUtil = fileUtil;
		this.xmlparser = xmlparser;
		this.validation = validation;
		this.modelMapper = modelMapper;
		this.carRepository = carRepository;
		this.racerRepository = racerRepository;
	}

	@Override
	public Boolean raceEntriesAreImported() {
		return this.raceEntryRepository.count() > 0;
	}

	@Override
	public String readRaceEntriesXmlFile() {
		try {
			return this.fileUtil.readFile(RACES_Enties_JSON_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String importRaceEntries() {
		StringBuilder importResult = new StringBuilder();
		try {
			RaceEntriesXmlDtos raceEntries = this.xmlparser.parseXml(RaceEntriesXmlDtos.class,
					RACES_Enties_JSON_FILE_PATH);
			RaceEntryXmlDto[] raceEntriesArr = new RaceEntryXmlDto[raceEntries.getEntries().size()];
			raceEntriesArr = raceEntries.getEntries().toArray(raceEntriesArr);
			// RaceEntryXmlDto[] raceEntriesArr = .toArray(RaceEntryXmlDto[].cl);
			Arrays.stream(raceEntriesArr).forEach(raceEntryImportDto -> {
				if (!(this.validation.isValid(raceEntryImportDto))) {
					importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
					return;
				}
				RaceEntry raceEntriesEntity = new RaceEntry();
				raceEntriesEntity.setFinishTime((float) raceEntryImportDto.getFinishTime());
				raceEntriesEntity.setHasFinished(raceEntryImportDto.isHasFinished());
				Car car = this.carRepository.findById(raceEntryImportDto.getCarId()).orElse(null);
				if (car != null)
					raceEntriesEntity.setCar(car);
				Racer racer = this.racerRepository.findByName(raceEntryImportDto.getRacer()).orElse(null);
				if (racer != null)
					raceEntriesEntity.setRacer(racer);
				else {
					racer.setName(raceEntryImportDto.getRacer());
					raceEntriesEntity.setRacer(racer);
				}
				this.raceEntryRepository.saveAndFlush(raceEntriesEntity);
				importResult
						.append((String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
								raceEntriesEntity.getClass().getSimpleName(), raceEntriesEntity)))
						.append(System.lineSeparator());
			});
			return importResult.toString().trim();
		} catch (

		JAXBException e) {
			e.printStackTrace();
		}
		return null;

	}

}
