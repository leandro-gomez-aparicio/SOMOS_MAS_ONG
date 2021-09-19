package com.somosmas.app.controller;

import com.somosmas.app.exception.custom.CategoryAlreadyExistException;
import com.somosmas.app.model.request.CategoryRequest;
import com.somosmas.app.model.response.CategoryResponse;
import com.somosmas.app.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) throws CategoryAlreadyExistException {
        categoryService.create(categoryRequest);
        return new ResponseEntity<>(categoryRequest, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findBy(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findBy(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCategoryName() {
        return new ResponseEntity<>(categoryService.getCategoryName(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequest category, @PathVariable Long id){
        CategoryResponse categoryUpdated = categoryService.update(category, id);
        return new ResponseEntity<>(categoryUpdated, HttpStatus.CREATED);
    }
}
