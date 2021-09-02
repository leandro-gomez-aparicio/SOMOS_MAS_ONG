package com.somosmas.app.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "slide")
public class Slide implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, name = "image_url")
	private String imageUrl;

	@Column(nullable = false, name = "text")
	private String text;

	@Column(name = "order")
	private Integer order;

	@Column(name = "organization_id")
	private Long organizationId;

	public Slide(String imageUrl, String text, Integer order, Long organizationId) {
		this.imageUrl = imageUrl;
		this.text = text;
		this.order = order;
		this.organizationId = organizationId;
	}

	public Slide() {
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
