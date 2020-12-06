package com.example.demo.domain.dtos;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;

public class RacerImportDto {

	@Expose
	private String name;
	@Expose
	private int age;
	@Expose
	private BigDecimal bounty;
	@Expose
	private String homeTown;

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getBounty() {
		return bounty;
	}

	public void setBounty(BigDecimal bounty) {
		this.bounty = bounty;
	}

	@NotNull
	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
}
