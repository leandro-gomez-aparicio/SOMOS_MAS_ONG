package com.somosmas.app.model.response;

import java.util.List;

public class ListCategoryResponse {

    private List<CategoryResponse> categories;

    public ListCategoryResponse() {

    }

    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }

}
