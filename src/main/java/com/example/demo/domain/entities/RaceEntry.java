package com.example.demo.domain.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "race_entries")
public class RaceEntry extends BaseEntity {

	private Boolean hasFinished;

	private Float finishTime;

	@ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;

	@ManyToOne(targetEntity = Race.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "race_id", referencedColumnName = "id")
	private Race race;

	@ManyToOne(targetEntity = Racer.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "racer_id", referencedColumnName = "id")
	private Racer racer;

	public RaceEntry() {
		super();
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Boolean getHasFinished() {
		return hasFinished;
	}

	public void setHasFinished(Boolean hasFinished) {
		this.hasFinished = hasFinished;
	}

	public Float getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Float finishTime) {
		this.finishTime = finishTime;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Racer getRacer() {
		return racer;
	}

	public void setRacer(Racer racer) {
		this.racer = racer;
	}

}
