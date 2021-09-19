package com.somosmas.app.model.request;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UpdateOrganizationRequest {

	private Long idOrganization;

	@NotEmpty(message = "No puede ser Null")
	private String name;

	@NotEmpty(message = "No puede ser Null")
	private String image;

	private String address;

	private Integer phone;

	@Email
	@NotEmpty(message = "No puede ser Null")
	private String email;

	@NotEmpty(message = "No puede ser Null")
	private String welcomeText;

	private String aboutUsText;
	
    private String facebookURL;
    
    private String linkedInURL;
    
    private String instagramURL;

	public UpdateOrganizationRequest() {
		super();
	}

	public Long getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}

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
	
	public String getFacebookURL() {
		return facebookURL;
	}

	public void setFacebookURL(String facebookURL) {
		this.facebookURL = facebookURL;
	}

	public String getLinkedInURL() {
		return linkedInURL;
	}

	public void setLinkedInURL(String linkedInURL) {
		this.linkedInURL = linkedInURL;
	}

	public String getInstagramURL() {
		return instagramURL;
	}

	public void setInstagramURL(String instagramURL) {
		this.instagramURL = instagramURL;
	}

}
