package com.bollock.blockcar.post;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.bollock.blockcar.car.Car;
import com.bollock.blockcar.user.User;

@Entity
public class Post {
	@Id @GeneratedValue
	private Long no;
	
	@ManyToOne
	private User user;
	
	private String carno;
	
	private int price;
	
	@Lob
	private String picture;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date = new Date();
	
	private String state;
	
	@Transient
	private Car car;

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

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}
