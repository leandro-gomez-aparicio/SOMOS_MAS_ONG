package com.somosmas.app.model.response;

import java.sql.Timestamp;

public class NewsResponse {

    private Long idNews;

    private String name;

    private String content;

    private String image;

    private Timestamp timestamp;

    private CategoryResponse category;

    public NewsResponse() {
        super();
    }

    public NewsResponse(Long idNews, String name, String content, String image, Timestamp timestamp, Boolean softDelete,
                        CategoryResponse category) {
        super();
        this.idNews = idNews;
        this.name = name;
        this.content = content;
        this.image = image;
        this.timestamp = timestamp;
        this.category = category;
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

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

}
