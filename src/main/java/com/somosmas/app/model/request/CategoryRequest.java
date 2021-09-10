package com.somosmas.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryRequest {

	@NotEmpty(message = "Name cannot be empty")
	@NotNull(message = "Name cannot be null.")
	private String name;
	
	@NotEmpty(message = "Description cannot be empty")
	@NotNull(message = "Description cannot be null.")
	private String description;

	@NotEmpty(message = "Image cannot be empty")
	@NotNull(message = "Image cannot be null.")
	private String image;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
