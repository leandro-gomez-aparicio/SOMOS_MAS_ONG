package com.somosmas.app.service;

import com.somosmas.app.exception.CategoryAlreadyExistException;
import com.somosmas.app.model.entity.Category;
import com.somosmas.app.model.request.CategoryRequest;
import com.somosmas.app.model.response.CategoryResponse;
import com.somosmas.app.model.response.ListCategoryResponse;
import com.somosmas.app.repository.ICategoryRepository;
import com.somosmas.app.service.abstraction.ICategoryService;
import com.somosmas.app.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final String CATEGORY_ID_NOT_FOUND = "Category ID: {0} not found.";

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public void delete(Long id) throws NoSuchElementException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(CATEGORY_ID_NOT_FOUND, id)));
        category.setSoftDelete(true);
        categoryRepository.save(category);
    }

    @Override
    public ListCategoryResponse getCategoryName() {
        List<Category> categories = categoryRepository.findAll();

        ListCategoryResponse response = new ListCategoryResponse();
        if (categories.isEmpty()) {
            return response;
        }

        List<CategoryResponse> categoriesResponses = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setName(category.getName());
            categoriesResponses.add(categoryResponse);
        }

        response.setCategories(categoriesResponses);
        return response;

    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findByIdCategory(Long idCategory) {
        Category category = categoryRepository.findByIdCategory(idCategory)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(CATEGORY_ID_NOT_FOUND, idCategory)));

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setImage(category.getImage());
        categoryResponse.setIdCategory(category.getIdCategory());
        return categoryResponse;
    }

    @Override
    public void create(CategoryRequest categoryRequest) throws CategoryAlreadyExistException {
        if ((categoryRepository.findByName(categoryRequest.getName()).isPresent())) {
            throw new CategoryAlreadyExistException(categoryRequest.getName());
        }
        Category category = ConvertUtil.convertToEntity(categoryRequest);
        category.setTimeStamp(Timestamp.from(Instant.now()));
        category.setSoftDelete(false);
        categoryRepository.save(category);
    }


}
