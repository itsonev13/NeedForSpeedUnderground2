package com.example.demo.service;

import java.io.IOException;

public interface CarService {

	Boolean carsAreImported();

	String readCarsJsonFile() throws IOException;

	String importCars(String carsFileContent);
}
