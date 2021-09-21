package com.somosmas.app.model.response;

public class SocialMediaResponse {
	
    private String facebookURL;
    
    private String linkedInURL;
    
    private String instagramURL;

	public SocialMediaResponse() {
	}

	public SocialMediaResponse(String facebookURL, String instagramURL, String linkedInURL) {
		this.facebookURL = facebookURL;
		this.instagramURL = instagramURL;
		this.linkedInURL = linkedInURL;
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
