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
@Table(name = "racers")
public class Racer extends BaseEntity {
	@Expose
	@Column(nullable = false, unique = true)
	private String name;

	@Expose
	@Column
	private Integer age;

	@Expose
	@Column
	private BigDecimal bounty;

	@ManyToOne(targetEntity = Town.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "hometown_id", referencedColumnName = "id")
	private Town hometown;

	@OneToMany(targetEntity = Car.class, mappedBy = "racer")
	@Expose
	private Set<Car> cars;

	@OneToMany(targetEntity = RaceEntry.class, mappedBy = "racer")
	private Set<RaceEntry> entries;

	public Racer() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getBounty() {
		return bounty;
	}

	public void setBounty(BigDecimal bounty) {
		this.bounty = bounty;
	}

	public Town getHometown() {
		return hometown;
	}

	public void setHometown(Town hometown) {
		this.hometown = hometown;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

}
