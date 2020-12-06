package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants;
import com.example.demo.domain.dtos.EntryXmlDto;
import com.example.demo.domain.dtos.RaceXmlDto;
import com.example.demo.domain.dtos.RacesXmlDtos;
import com.example.demo.domain.entities.District;
import com.example.demo.domain.entities.Race;
import com.example.demo.domain.entities.RaceEntry;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.RaceEntryRepository;
import com.example.demo.repository.RaceRepository;
import com.example.demo.repository.RacerRepository;
import com.example.demo.util.FileUtil;
import com.example.demo.util.ValidationUtil;
import com.example.demo.util.XmlParser;

@Service
public class RaceServiceImpl implements RaceService {

	private final static String RACES_JSON_FILE_PATH = System.getProperty("user.dir")
			+ "//src//main//resources//files//races.xml";
	private RaceRepository raceRepository;
	private RaceEntryRepository raceEntryRepository;
	private RacerRepository racerRepository;
	private CarRepository carRepository;
	private DistrictRepository districtRepository;
	private FileUtil fileUtil;
	private final XmlParser xmlparser;
	private final ValidationUtil validation;
	private final ModelMapper modelMapper;

	@Autowired
	public RaceServiceImpl(RaceRepository raceRepository, RaceEntryRepository raceEntryRepository,
			RacerRepository racerRepository, CarRepository carRepository, FileUtil fileUtil, XmlParser xmlparser,
			ValidationUtil validation, ModelMapper modelMapper, DistrictRepository districtRepository) {
		super();
		this.raceRepository = raceRepository;
		this.raceEntryRepository = raceEntryRepository;
		this.racerRepository = racerRepository;
		this.carRepository = carRepository;
		this.fileUtil = fileUtil;
		this.xmlparser = xmlparser;
		this.validation = validation;
		this.modelMapper = modelMapper;
		this.districtRepository = districtRepository;
	}

	@Override
	public Boolean racesAreImported() {
		return this.raceRepository.count() > 0;
	}

	@Override
	public String readRacesXmlFile() {
		try {
			return this.fileUtil.readFile(RACES_JSON_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String importRaces() {
		StringBuilder importResult = new StringBuilder();
		try {
			RacesXmlDtos races = this.xmlparser.parseXml(RacesXmlDtos.class, RACES_JSON_FILE_PATH);
			RaceXmlDto[] raceArr = new RaceXmlDto[races.getRaces().size()];
			raceArr = races.getRaces().toArray(raceArr);
			Arrays.stream(raceArr).forEach(raceImportDto -> {
				if (!(this.validation.isValid(raceImportDto))) {
					importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
					return;
				}
				Race raceEntity = new Race();
				raceEntity.setLaps(raceImportDto.getLaps());
				District district = this.districtRepository.findByName(raceImportDto.getDistrictName()).orElse(null);
				raceEntity.setDistrict(district);
				Set<RaceEntry> entries = new HashSet<RaceEntry>();
				for (EntryXmlDto e : raceImportDto.getEntry()) {
					RaceEntry raceEntry = this.raceEntryRepository.findById(e.getId()).orElse(null);
					entries.add(raceEntry);
				}
				raceEntity.setEntries(entries);

				this.raceRepository.saveAndFlush(raceEntity);
				importResult.append((String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
						raceEntity.getClass().getSimpleName(), raceEntity))).append(System.lineSeparator());
			});
			return importResult.toString().trim();
		} catch (

		JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
