package com.example.demo.service;

public interface RacerService {

	Boolean racersAreImported();

	String readRacersJsonFile();

	String importRacers(String racersFileContent);

	String exportRacingCars();
}
