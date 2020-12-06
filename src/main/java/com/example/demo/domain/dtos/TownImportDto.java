package com.example.demo.domain.dtos;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;

public class TownImportDto {
	@Expose
	private String name;

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TownImportDto() {

	}

}
