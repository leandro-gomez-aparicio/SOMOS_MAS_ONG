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

	private SocialMediaResponse socialMedia;
	
	private Timestamp timestamp;
	
	private Boolean softDelete;
	
	public UpdateOrganizationResponse() {
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

	public SocialMediaResponse getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(SocialMediaResponse socialMedia) {
		this.socialMedia = socialMedia;
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
