package com.somosmas.app.controller;

import com.somosmas.app.exception.ConstraintViolationException;
import com.somosmas.app.model.request.LoginRequest;
import com.somosmas.app.service.ILoginService;
import com.somosmas.app.exception.AuthenticationDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    ILoginService loginService;

    @PostMapping(value = "/login",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        try{
            return ResponseEntity.ok(loginService.authentication(loginRequest));
        }catch (AuthenticationDeniedException e){
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (ConstraintViolationException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
