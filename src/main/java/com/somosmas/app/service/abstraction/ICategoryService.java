package com.somosmas.app.service.abstraction;

import java.util.List;

import com.somosmas.app.model.response.CategoryResponse;

public interface ICategoryService {

    void delete(Long id);

	List<CategoryResponse> getCategoryName();
    
}
