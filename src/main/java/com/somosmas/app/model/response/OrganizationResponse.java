package com.somosmas.app.model.response;

import java.util.List;

public class OrganizationResponse {

	private String name;
	private String image;
	private String address;
	private Integer phone;
	private SocialMediaResponse socialMedia;
	private List<SlideResponse> slides;

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
	
	public SocialMediaResponse getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(SocialMediaResponse socialMedia) {
		this.socialMedia = socialMedia;
	}

	public List<SlideResponse> getSlides() {
		return slides;
	}

	public void setSlides(List<SlideResponse> slides) {
		this.slides = slides;
	}
}
