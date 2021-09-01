package com.somosmas.app.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "organization")
public class Organization {

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private Integer phone;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "welcome_text", nullable = false)
	private String welcomeText;

	@Column(name = "about_us_text")
	private String aboutUsText;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	@Column(name = "soft_delete")
	private Boolean softDelete;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWelcomeText() {
		return welcomeText;
	}

	public void setWelcomeText(String welcomeText) {
		this.welcomeText = welcomeText;
	}

	public String getAboutUsText() {
		return aboutUsText;
	}

	public void setAboutUsText(String aboutUsText) {
		this.aboutUsText = aboutUsText;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(Boolean softDelete) {
		this.softDelete = softDelete;
	}


}