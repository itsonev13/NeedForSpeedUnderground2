package com.example.demo.domain.dtos;

import com.google.gson.annotations.Expose;

public class RaceEntryImportDto {

	@Expose
	private String racerName;
	@Expose
	private boolean hasFinished;
	@Expose
	private double finishTime;
	@Expose
	private int carId;

	public String getRacerName() {
		return racerName;
	}

	public void setRacerName(String racerName) {
		this.racerName = racerName;
	}

	public boolean isHasFinished() {
		return hasFinished;
	}

	public void setHasFinished(boolean hasFinished) {
		this.hasFinished = hasFinished;
	}

	public double getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(double finishTime) {
		this.finishTime = finishTime;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

}
