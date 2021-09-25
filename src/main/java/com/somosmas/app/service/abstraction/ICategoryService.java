package com.somosmas.app.service.abstraction;


import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;

import com.somosmas.app.exception.custom.CategoryAlreadyExistException;
import com.somosmas.app.model.request.CategoryRequest;
import com.somosmas.app.model.response.CategoryResponse;
import com.somosmas.app.model.response.ListCategoryResponse;


public interface ICategoryService {

    void create(CategoryRequest categoryRequest) throws CategoryAlreadyExistException;

    void delete(Long id);
    
    CategoryResponse update(CategoryRequest category, Long id);

    CategoryResponse findBy(Long id);

    Page<ListCategoryResponse> listCategoryNames(Integer page)throws NotFoundException;

}
