package com.somosmas.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TestimonialsRequest {
	
	@NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotEmpty(message = "Content cannot be empty")
    @NotNull(message = "Content cannot be null.")
    private String content;
    
    private String image;
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
