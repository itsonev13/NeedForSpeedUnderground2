package com.example.demo.domain.dtos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesXmlDtos {
	@XmlElement(name = "race-entry")
	private List<RaceEntryXmlDto> entries;

	public List<RaceEntryXmlDto> getEntries() {
		return entries;
	}

	public void setEntries(List<RaceEntryXmlDto> entries) {
		this.entries = entries;
	}

}
