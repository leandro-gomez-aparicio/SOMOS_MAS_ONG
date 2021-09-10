package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.response.ListCategoryResponse;

public interface ICategoryService {

    void delete(Long id);

    ListCategoryResponse getCategoryName();

}
