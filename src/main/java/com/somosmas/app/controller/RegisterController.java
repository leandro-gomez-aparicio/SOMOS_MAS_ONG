package com.somosmas.app.controller;

import com.somosmas.app.exception.custom.SendEmailException;
import com.somosmas.app.exception.custom.UserAlreadyExistException;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.service.abstraction.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class RegisterController {
	private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@Valid @RequestBody UserDetailsRequest registerUserRequest)
            throws UserAlreadyExistException {
    	UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
    	try {
    		userDetailsResponse = userService.register(registerUserRequest);
    	} catch (SendEmailException e) {
    		LOG.error(e.getMessage());
    	}
    	return new ResponseEntity<>(userDetailsResponse, HttpStatus.CREATED);
    }

}
