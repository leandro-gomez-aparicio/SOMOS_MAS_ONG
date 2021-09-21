package com.somosmas.app.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UpdateOrganizationRequest {

    private Long idOrganization;

    @NotEmpty(message = "Cannot be null.")
    private String name;

    @NotEmpty(message = "Cannot be null.")
    private String image;

    private String address;

    private Integer phone;

    @Email
    @NotEmpty(message = "Cannot be null.")
    private String email;

    @NotEmpty(message = "Cannot be null.")
    private String welcomeText;

    private String aboutUsText;

    private SocialMediaRequest socialMedia;

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

    public SocialMediaRequest getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMediaRequest socialMedia) {
        this.socialMedia = socialMedia;
    }
}
