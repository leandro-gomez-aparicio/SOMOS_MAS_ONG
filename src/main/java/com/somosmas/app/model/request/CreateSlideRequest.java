package com.somosmas.app.model.request;

import javax.validation.constraints.NotNull;

public class CreateSlideRequest {
    private String encodedImage;
    private Integer slideOrder;
    @NotNull(message = "description cannot be null")
    private String description;
    private String contentType;

    public CreateSlideRequest() {
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public Integer getSlideOrder() {
        return slideOrder;
    }

    public void setSlideOrder(Integer slideOrder) {
        this.slideOrder = slideOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
