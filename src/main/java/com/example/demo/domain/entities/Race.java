package com.example.demo.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "races")
public class Race extends BaseEntity {

	@Column(name = "laps", nullable = false, columnDefinition = "INT(11)default 0")
	private Integer laps;

	@ManyToOne(targetEntity = District.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", referencedColumnName = "id")
	private District district;

	@OneToMany(targetEntity = RaceEntry.class, mappedBy = "race")
	private Set<RaceEntry> entries;

	public Race() {
		super();
	}

	public Integer getLaps() {
		return laps;
	}

	public void setLaps(Integer laps) {
		this.laps = laps;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Set<RaceEntry> getEntries() {
		return entries;
	}

	public void setEntries(Set<RaceEntry> entries) {
		this.entries = entries;
	}

}
