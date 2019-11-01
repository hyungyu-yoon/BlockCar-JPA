package com.bollock.blockcar.car;

public class Record {
	private String carSerialNumber;
	private String carData;
	private String createdAt;
	private String expiredAt;
	public Record(String carSerialNumber, String carData, String createdAt, String expiredAt) {
		super();
		this.carSerialNumber = carSerialNumber;
		this.carData = carData;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
	}
	public Record() {
		super();
	}
	public String getCarSerialNumber() {
		return carSerialNumber;
	}
	public void setCarSerialNumber(String carSerialNumber) {
		this.carSerialNumber = carSerialNumber;
	}
	public String getCarData() {
		return carData;
	}
	public void setCarData(String carData) {
		this.carData = carData;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(String expiredAt) {
		this.expiredAt = expiredAt;
	}
	@Override
	public String toString() {
		return "Accident [carSerialNumber=" + carSerialNumber + ", carData=" + carData + ", createdAt=" + createdAt
				+ ", expiredAt=" + expiredAt + "]";
	}
	
}
