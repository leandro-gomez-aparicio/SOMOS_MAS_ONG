package com.somosmas.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "testimonials")
public class Testimonials implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, name = "id_testimonials")
    private Long idTestimonials;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "softDelete")
    private Boolean softDelete;

    public Testimonials() {
    }

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(Boolean softDelete) {
        this.softDelete = softDelete;
    }

}
