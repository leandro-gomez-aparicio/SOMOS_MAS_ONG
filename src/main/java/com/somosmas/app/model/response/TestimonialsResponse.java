package com.somosmas.app.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestimonialsResponse {
    
    private Long idTestimonials;

    private String name;

    private String image;

    private String content;

    public Long getIdTestimonials() {
        return idTestimonials;
    }

    public void setIdTestimonials(Long idTestimonials) {
        this.idTestimonials = idTestimonials;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
