package com.somosmas.app.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activity")
public class Activity {
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "image", nullable = false)
	private String image;
	
	@Column(name = "timestamp")
	private Timestamp timestamp;
	
	@Column(name = "soft_delete")
	private boolean softDelete;

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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isSoftDelete() {
		return softDelete;
	}

	public void setSoftDeleted(boolean softDelete) {
		this.softDelete = softDelete;
	}
		
}
