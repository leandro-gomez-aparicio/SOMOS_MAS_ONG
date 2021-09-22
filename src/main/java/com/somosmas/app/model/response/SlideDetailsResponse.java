package com.somosmas.app.model.response;

public class SlideDetailsResponse {
	
    private Long idSlide;
    
    private String imageUrl;
    
    private String text;
    
    private Integer slideOrder;

    private Long organizationId;

	public Long getIdSlide() {
		return idSlide;
	}

	public void setIdSlide(Long idSlide) {
		this.idSlide = idSlide;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getSlideOrder() {
		return slideOrder;
	}

	public void setSlideOrder(Integer slideOrder) {
		this.slideOrder = slideOrder;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
    
}
