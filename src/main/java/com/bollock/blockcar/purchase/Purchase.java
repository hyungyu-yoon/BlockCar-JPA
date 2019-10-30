package com.bollock.blockcar.purchase;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bollock.blockcar.post.Post;
import com.bollock.blockcar.user.User;

@Entity
public class Purchase {
	@Id @GeneratedValue
	private Long no;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Post post;
	
	private String name;
	private String phone;
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date application_date = new Date();
	
	@Temporal(TemporalType.DATE)
	private Date delivery_date;
	
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getApplication_date() {
		return application_date;
	}

	public void setApplication_date(Date application_date) {
		this.application_date = application_date;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
