package com.bollock.blockcar.auth;

public class Auth {
	private String name;
	private String phone;
	private String car_no;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCar_no() {
		return car_no;
	}
	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}
	@Override
	public String toString() {
		return "Auth [name=" + name + ", phone=" + phone + ", car_no=" + car_no + "]";
	}
	
}
