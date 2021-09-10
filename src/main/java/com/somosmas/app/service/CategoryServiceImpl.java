package com.somosmas.app.service;

import com.somosmas.app.model.entity.Category;
import com.somosmas.app.model.response.CategoryResponse;
import com.somosmas.app.model.response.ListCategoryResponse;
import com.somosmas.app.repository.ICategoryRepository;
import com.somosmas.app.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
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

}
