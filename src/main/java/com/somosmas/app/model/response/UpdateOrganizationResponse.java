package com.somosmas.app.model.response;

import java.sql.Timestamp;

public class UpdateOrganizationResponse {

	private Long idOrganization;
	
	private String name;
	
	private String image;
	
	private String address;
	
	private Integer phone;
	
	private String email;
	
	private String welcomeText;
	
	private String aboutUsText;
	
	private String facebookURL;

	private String linkedInURL;
    
    private String instagramURL;
	
	private Timestamp timestamp;
	
	private Boolean softDelete;
	
	public UpdateOrganizationResponse() {
		super();
	}

	public UpdateOrganizationResponse(Long idOrganization, String name, String image, String address, Integer phone,
			String email, String welcomeText, String aboutUsText, String facebookURL, String linkedInURL, String instagramURL, Timestamp timestamp, Boolean softDelete) {
		super();
		this.idOrganization = idOrganization;
		this.name = name;
		this.image = image;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.welcomeText = welcomeText;
		this.aboutUsText = aboutUsText;
		this.facebookURL = facebookURL;
		this.linkedInURL = linkedInURL;
		this.instagramURL = instagramURL;
		this.timestamp = timestamp;
		this.softDelete = softDelete;
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
