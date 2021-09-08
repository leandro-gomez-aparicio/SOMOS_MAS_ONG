package com.somosmas.app.service;

import com.somosmas.app.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.somosmas.app.model.entity.Category;
import com.somosmas.app.repository.ICategoryRepository;
import java.text.MessageFormat;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final String CATEGORY_ID_NOT_FOUND = "Category ID: {0} not found.";

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public void delete(Long id) throws NoSuchElementException{
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(MessageFormat.format(CATEGORY_ID_NOT_FOUND, id)));
        category.setSoftDelete(true);
        categoryRepository.save(category);
    }
    
}