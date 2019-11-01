package com.bollock.blockcar.car;

public class CarSerial {
	private String carSerialNumber;
	private String carNumber;
	private String createAt;
	private String expireAt;
	public CarSerial(String carSerialNumber, String carNumber, String createAt, String expireAt) {
		super();
		this.carSerialNumber = carSerialNumber;
		this.carNumber = carNumber;
		this.createAt = createAt;
		this.expireAt = expireAt;
	}
	public CarSerial() {
		super();
	}
	public String getCarSerialNumber() {
		return carSerialNumber;
	}
	public void setCarSerialNumber(String carSerialNumber) {
		this.carSerialNumber = carSerialNumber;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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
		return "CarSerial [carSerialNumber=" + carSerialNumber + ", carNumber=" + carNumber + ", createAt=" + createAt
				+ ", expireAt=" + expireAt + "]";
	}
	
}
