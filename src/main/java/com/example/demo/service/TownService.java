package com.example.demo.service;

public interface TownService {

	Boolean townsAreImported();

	String readTownsJsonFile();

	String importTowns(String townsFileContent);

	String exportRacingTowns();
}
