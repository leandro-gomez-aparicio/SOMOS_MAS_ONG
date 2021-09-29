package com.somosmas.app.controller;

import com.somosmas.app.exception.custom.SlideOrderAlreadyExistsException;
import com.somosmas.app.model.request.CreateSlideRequest;
import com.somosmas.app.service.abstraction.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private ISlideService slideService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(slideService.list(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        slideService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findBy(@PathVariable("id") Long id){
    	return new ResponseEntity<>(slideService.findBy(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody @Valid CreateSlideRequest request) throws IOException {
        return new ResponseEntity<>(slideService.create(request),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody @Valid CreateSlideRequest request,@PathVariable Long id) throws SlideOrderAlreadyExistsException {
        return new ResponseEntity<>(slideService.update(request,id),HttpStatus.OK);
    }
}
