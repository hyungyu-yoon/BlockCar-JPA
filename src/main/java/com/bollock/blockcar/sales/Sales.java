package com.bollock.blockcar.sales;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bollock.blockcar.user.User;

@Entity
public class Sales {
	@Id
	@GeneratedValue
	private Long no;

	@ManyToOne
	private User user;

	private String phone;
	private String address;
	private String carno;
	private Long price;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date application_date = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pickup_date;
	
	private String state;


	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getApplication_date() {
		return application_date;
	}

	public void setApplication_date(Date application_date) {
		this.application_date = application_date;
	}

	public Date getPickup_date() {
		return pickup_date;
	}

	public void setPickup_date(Date pickup_date) {
		this.pickup_date = pickup_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Sales [salesno=" + no + ", user=" + user + ", application_date=" + application_date
				+ ", pickup_date=" + pickup_date + "]";
	}
	
	
}
