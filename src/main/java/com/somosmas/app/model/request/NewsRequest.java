package com.somosmas.app.model.request;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

public class NewsRequest {

    @NotEmpty(message = "Cannot be null or empty.")
    private String name;

    @NotEmpty(message = "Cannot be null or empty.")
    private String content;

    @NotEmpty(message = "Cannot be null or empty.")
    private String image;

    private Timestamp timestamp;

    private Boolean softDelete = Boolean.FALSE;

    private Long idCategory;

    public NewsRequest() {
        super();
    }

    public NewsRequest(@NotEmpty(message = "Cannot be null or empty.") String name,
                       @NotEmpty(message = "Cannot be null or empty.") String content,
					   @NotEmpty(message = "Cannot be null or empty.") String image,
                       Timestamp timestamp, Boolean softDelete, Long idCategory) {
        super();
        this.name = name;
        this.content = content;
        this.image = image;
        this.timestamp = timestamp;
        this.softDelete = softDelete;
        this.idCategory = idCategory;
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

    public Boolean getSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(Boolean softDelete) {
        this.softDelete = softDelete;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

}
