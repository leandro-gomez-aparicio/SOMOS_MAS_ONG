package com.somosmas.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somosmas.app.exception.custom.ActivityAlreadyExistException;
import com.somosmas.app.model.request.ActivityRequest;
import com.somosmas.app.service.abstraction.IActivityService;

@RestController
@RequestMapping("/activities")

public class ActivitiesController {
	
	@Autowired
	private IActivityService activityService;
	
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create (@Valid @RequestBody ActivityRequest activityRequest) throws ActivityAlreadyExistException{
		activityService.create(activityRequest);
		return new ResponseEntity<>(activityRequest, HttpStatus.CREATED);
	}

}
