package com.example.demo.domain.dtos;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;

public class CarsImportDto {

	@Expose
	private String brand;
	@Expose
	private String model;
	@Expose
	private BigDecimal price;
	@Expose
	private int yearOfProduction;
	@Expose
	private String racerName;

	@NotNull
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@NotNull
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@NotNull
	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public String getRacerName() {
		return racerName;
	}

	public void setRacerName(String racerName) {
		this.racerName = racerName;
	}

}
