package com.somosmas.app.controller;

import com.somosmas.app.exception.UserAlreadyExistException;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.service.abstraction.IUserService;
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

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@Valid @RequestBody UserDetailsRequest registerUserRequest)
            throws UserAlreadyExistException {
        UserDetailsResponse userDetailsResponse = userService.register(registerUserRequest);
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.CREATED);
    }

}
