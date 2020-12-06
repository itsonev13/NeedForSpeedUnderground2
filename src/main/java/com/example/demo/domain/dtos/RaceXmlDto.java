package com.example.demo.domain.dtos;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXmlDto implements Serializable {

	@XmlElement(name = "laps")

	private int laps;

	@XmlElement(name = "district-name")

	private String districtName;

	@XmlElementWrapper(name = "entries")
	@XmlElement(name = "entry")
	private List<EntryXmlDto> entry;

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public List<EntryXmlDto> getEntry() {
		return entry;
	}

	public void setEntry(List<EntryXmlDto> entry) {
		this.entry = entry;
	}

}
