package com.somosmas.app.controller;

import com.somosmas.app.exception.custom.ActivityAlreadyExistException;
import com.somosmas.app.model.request.ActivityRequest;
import com.somosmas.app.model.response.ActivityResponse;
import com.somosmas.app.service.abstraction.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody ActivityRequest activityRequest) throws ActivityAlreadyExistException {
        activityService.create(activityRequest);
        return new ResponseEntity<>(activityRequest, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody ActivityRequest activity, @PathVariable Long id){
		ActivityResponse activityResponse = activityService.update(activity, id);
    	return new ResponseEntity<>(activityResponse, HttpStatus.OK);
    	
    }
}
