package com.example.demo.domain.dtos;

import javax.xml.bind.annotation.XmlAttribute;

public class EntryXmlDto {
	@XmlAttribute(name = "id")
	private Integer id1;

	public Integer getId() {
		return id1;
	}

	public void setId(Integer id) {
		this.id1 = id;
	}
}
