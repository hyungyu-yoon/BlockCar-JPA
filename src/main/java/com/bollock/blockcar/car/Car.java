package com.bollock.blockcar.car;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class Car {
	@Id
	@Column(length = 20)
	private String carSerial;

	@Lob
	private String info;
	private String carNumber;

	@Transient
	private List<Record> accident;
	@Transient
	private List<Record> maintenance;
	@Transient
	private List<Record> owner;
	
	@Transient
	private String createAt;
	@Transient
	private String expireAt;
	
	
	public Car(String carSerial, String info, String carNumber, List<Record> accident, List<Record> maintenance,
			List<Record> owner) {
		super();
		this.carSerial = carSerial;
		this.info = info;
		this.carNumber = carNumber;
		this.accident = accident;
		this.maintenance = maintenance;
		this.owner = owner;
	}
	public Car() {
		super();
	}
	public Car(String carSerial, String carNumber, String createAt, String expireAt) {
		this.carSerial = carSerial;
		this.carNumber = carNumber;
		this.createAt = createAt;
		this.expireAt = expireAt;
	}
	public String getCarSerial() {
		return carSerial;
	}
	public void setCarSerial(String carSerial) {
		this.carSerial = carSerial;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public List<Record> getAccident() {
		return accident;
	}
	public void setAccident(List<Record> accident) {
		this.accident = accident;
	}
	public List<Record> getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(List<Record> maintenance) {
		this.maintenance = maintenance;
	}
	public List<Record> getOwner() {
		return owner;
	}
	public void setOwner(List<Record> owner) {
		this.owner = owner;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public String getExpireAt() {
		return expireAt;
	}
	public void setExpireAt(String expireAt) {
		this.expireAt = expireAt;
	}
	@Override
	public String toString() {
		return "Car [carSerial=" + carSerial + ", info=" + info + ", carNumber=" + carNumber + ", accident=" + accident
				+ ", maintenance=" + maintenance + ", owner=" + owner + ", createAt=" + createAt + ", expireAt="
				+ expireAt + "]";
	}

}
