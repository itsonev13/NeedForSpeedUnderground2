package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants;
import com.example.demo.domain.dtos.DistrictImportDto;
import com.example.demo.domain.dtos.TownImportDto;
import com.example.demo.domain.entities.District;
import com.example.demo.domain.entities.Town;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.TownRepository;
import com.example.demo.util.FileUtil;
import com.example.demo.util.ValidationUtil;
import com.google.gson.Gson;

@Service
public class DistrictServiceImpl implements DistrictService {

	private final static String DISTRICTS_JSON_FILE_PATH = System.getProperty("user.dir")
			+ "//src//main//resources//files//districts.json";
	private final DistrictRepository districtRepository;
	private final TownRepository townRepository;
	private FileUtil fileUtil;
	private final Gson gson;
	private final ValidationUtil validation;
	private final ModelMapper modelMapper;

	@Autowired
	public DistrictServiceImpl(DistrictRepository districtRepository, FileUtil fileUtil, Gson gson,
			ValidationUtil validation, ModelMapper modelMapper, TownRepository townRepository) {
		this.districtRepository = districtRepository;
		this.townRepository = townRepository;
		this.fileUtil = fileUtil;
		this.gson = gson;
		this.validation = validation;
		this.modelMapper = modelMapper;
	}

	@Override
	public Boolean districtsAreImported() {
		return this.districtRepository.count() > 0;
	}

	@Override
	public String readDistrictsJsonFile() {
		try {
			return this.fileUtil.readFile(DISTRICTS_JSON_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String importDistricts(String districtsFileContent) {
		StringBuilder importResult = new StringBuilder();
		DistrictImportDto[] districts = gson.fromJson(districtsFileContent, DistrictImportDto[].class);
		Arrays.stream(districts).forEach(districtImportDto -> {
			District districtEntity = this.districtRepository.findByName(districtImportDto.getName()).orElse(null);
			Town townEntity = this.townRepository.findByName(districtImportDto.getTownName()).orElse(null);
			if (districtEntity != null) {
				importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
				return;
			}
			if (!(this.validation.isValid(districtImportDto))) {
				importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
				return;
			}
			if (townEntity == null) {
				TownImportDto townImportDto = new TownImportDto();
				townImportDto.setName(districtImportDto.getTownName());
				townEntity = this.modelMapper.map(townImportDto, Town.class);
				this.townRepository.saveAndFlush(townEntity);
			}

			districtEntity = this.modelMapper.map(districtImportDto, District.class);
			districtEntity.setTown(townEntity);
			this.districtRepository.saveAndFlush(districtEntity);
			importResult
					.append((String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
							districtEntity.getClass().getSimpleName(), districtEntity.getName())))
					.append(System.lineSeparator());
		});
		return importResult.toString().trim();
	}

}
