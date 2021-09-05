package com.somosmas.app.controller;

import com.somosmas.app.exception.custom.AuthenticationDeniedException;
import com.somosmas.app.exception.custom.ConstraintViolationException;
import com.somosmas.app.model.request.LoginRequest;
import com.somosmas.app.service.abstraction.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    ILoginService loginService;

    @PostMapping(value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws AuthenticationDeniedException, ConstraintViolationException {
        return ResponseEntity.ok(loginService.authentication(loginRequest));
    }
}
