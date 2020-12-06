package com.example.demo.domain.dtos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacesXmlDtos {
	@XmlElement(name = "race")
	private List<RaceXmlDto> races;

	public List<RaceXmlDto> getRaces() {
		return races;
	}

	public void setRaces(List<RaceXmlDto> races) {
		this.races = races;
	}

}
