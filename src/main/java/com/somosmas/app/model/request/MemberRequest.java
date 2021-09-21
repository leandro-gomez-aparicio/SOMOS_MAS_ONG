package com.somosmas.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MemberRequest {
    
    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null.")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Name Input")
    private String name;
    
    private String facebookUrl;
    
    private String instagramUrl;
    
    private String linkedinUrl;
    
    @NotEmpty(message = "Image cannot be empty")
    @NotNull(message = "Image cannot be null.")
    private String image;
    
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
