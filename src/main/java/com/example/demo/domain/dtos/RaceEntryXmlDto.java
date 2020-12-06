package com.example.demo.domain.dtos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryXmlDto implements Serializable {

	@XmlAttribute(name = "has-finished")

	private boolean hasFinished;

	@XmlAttribute(name = "finish-time")

	private double finishTime;

	@XmlAttribute(name = "car-id")

	private int carId;

	@XmlElement(name = "racer")
	private String racer;

	public boolean isHasFinished() {
		return hasFinished;
	}

	public void setHasFinished(boolean hasFinished) {
		this.hasFinished = hasFinished;
	}

	public double getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(double finishTime) {
		this.finishTime = finishTime;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getRacer() {
		return racer;
	}

	public void setRacer(String racer) {
		this.racer = racer;
	}

}
