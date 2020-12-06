package com.example.demo.domain.dtos;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;

public class DistrictImportDto {
	@Expose
	private String name;
	@Expose
	private String townName;

	@NotNull
	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DistrictImportDto() {

	}
}
