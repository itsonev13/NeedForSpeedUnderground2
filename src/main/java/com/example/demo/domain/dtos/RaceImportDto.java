package com.example.demo.domain.dtos;

import java.util.List;

import com.google.gson.annotations.Expose;

public class RaceImportDto {
	@Expose
	private String districtName;
	@Expose
	private int laps;
	@Expose
	private List<Integer> entries;

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public List<Integer> getEntries() {
		return entries;
	}

	public void setEntries(List<Integer> entries) {
		this.entries = entries;
	}

}
