package com.example.demo.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

	@Column(nullable = false, unique = true)
	@Expose
	private String name;

	@OneToMany(targetEntity = District.class, mappedBy = "town")
	private Set<District> districts;

	@OneToMany(targetEntity = Racer.class, mappedBy = "hometown")
	private Set<Racer> racers;

	public Town() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<District> getDistricts() {
		return districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}

	public Set<Racer> getRacers() {
		return racers;
	}

	public void setRacers(Set<Racer> racers) {
		this.racers = racers;
	}

}
