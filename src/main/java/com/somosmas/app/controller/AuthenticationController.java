package com.somosmas.app.controller;

import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.service.abstraction.ILoginService;
import com.somosmas.app.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    IUserService userService;

    @Autowired
    ILoginService loginService;

    @PostMapping(value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody UserDetailsRequest userDetailsRequest) {
        return new ResponseEntity<>(loginService.authentication(userDetailsRequest), HttpStatus.OK);
    }

    @GetMapping(value ="/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsResponse> getAuthenticatedUserInformation(@RequestHeader(name = "Authorization", required = true) String token) {
        return ResponseEntity.ok(userService.getUserDetailsBy(token));
    }
}
