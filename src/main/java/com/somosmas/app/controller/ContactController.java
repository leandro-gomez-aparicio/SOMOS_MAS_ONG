package com.somosmas.app.controller;

import com.somosmas.app.exception.custom.ContactAlreadyExistException;
import com.somosmas.app.exception.custom.SendEmailException;
import com.somosmas.app.model.request.ContactRequest;
import com.somosmas.app.service.abstraction.IContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private IContactService contactService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody ContactRequest contactRequest) throws ContactAlreadyExistException {
        try {
            contactService.create(contactRequest);
        } catch (SendEmailException e) {
            LOG.error(e.getMessage());
        }
        return new ResponseEntity<>(contactRequest, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(contactService.list(), HttpStatus.OK);
    }

}