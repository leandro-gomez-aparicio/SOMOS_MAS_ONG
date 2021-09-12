package com.somosmas.app.service.abstraction;


import com.somosmas.app.exception.custom.CategoryAlreadyExistException;
import com.somosmas.app.model.request.CategoryRequest;
import com.somosmas.app.model.response.CategoryResponse;
import com.somosmas.app.model.response.ListCategoryResponse;


public interface ICategoryService {

    void create(CategoryRequest categoryRequest) throws CategoryAlreadyExistException;

    void delete(Long id);

    CategoryResponse findByIdCategory(Long idCategory);

    ListCategoryResponse getCategoryName();

}
