package com.somosmas.app.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_news")
    private Long idNews;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category category;

    public News() {
    }

    public Long getIdNews() {
        return idNews;
    }

    public void setIdNews(Long idNews) {
        this.idNews = idNews;
    }

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

    public Boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(Boolean softDelete) {
        this.softDelete = softDelete;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
