package com.somosmas.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somosmas.app.model.request.TestimonialsRequest;
import com.somosmas.app.model.response.TestimonialsResponse;
import com.somosmas.app.service.abstraction.ITestimonialsService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/testimonials")
public class TestimonialsController {

    @Autowired
    private ITestimonialsService testimonialsService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody TestimonialsRequest testimonialsRequest) {
        testimonialsService.create(testimonialsRequest);
        return new ResponseEntity<>(testimonialsRequest, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long idTestimonials) {
        testimonialsService.delete(idTestimonials);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody TestimonialsRequest testimonials, @PathVariable Long id){
        TestimonialsResponse testimonialsResponse = testimonialsService.update(testimonials, id);
        return new ResponseEntity<>(testimonialsResponse, HttpStatus.OK);
    }

}
