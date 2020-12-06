package com.example.demo.service;

import java.io.IOException;

public interface DistrictService {

	Boolean districtsAreImported();

	String readDistrictsJsonFile() throws IOException;

	String importDistricts(String districtsFileContent);
}
