package com.example.demo.domain.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
	@Expose
	@Column
	private String brand;

	@Expose
	@Column
	private String model;

	@Expose
	@Column
	private BigDecimal price;

	@Expose
	@Column
	private Integer yearOfProduction;

	@Expose
	@Column
	private Float maxSpeed;

	@Column
	private Float zeroToSixty;

	@ManyToOne(targetEntity = Racer.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "racer_id", referencedColumnName = "id")
	private Racer racer;

	@OneToMany(targetEntity = RaceEntry.class, mappedBy = "car")
	private Set<RaceEntry> entries;

	public Car() {
		super();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

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

	public Integer getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Integer yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public Float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Float getZeroToSixty() {
		return zeroToSixty;
	}

	public void setZeroToSixty(Float zeroToSixty) {
		this.zeroToSixty = zeroToSixty;
	}

	public Racer getRacer() {
		return racer;
	}

	public void setRacer(Racer racer) {
		this.racer = racer;
	}

}
