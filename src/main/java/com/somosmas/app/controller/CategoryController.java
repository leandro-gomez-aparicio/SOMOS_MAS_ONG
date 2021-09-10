package com.somosmas.app.controller;

import com.somosmas.app.service.abstraction.ICategoryService;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.somosmas.app.model.entity.Category;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("id") Long idCategory) {
		categoryService.delete(idCategory);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByIdCategory(@PathVariable Long idCategory){                
        return new ResponseEntity<>(categoryService.findByIdCategory(idCategory), HttpStatus.OK);
    }

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCategoryName() {
		return new ResponseEntity<>(categoryService.getCategoryName(), HttpStatus.OK);
	}
	
	
}
