package com.somosmas.app.controller;

import com.somosmas.app.model.request.UpdateOrganizationRequest;
import com.somosmas.app.model.response.UpdateOrganizationResponse;
import com.somosmas.app.service.abstraction.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrganizationDetails() {
        return new ResponseEntity<>(organizationService.getOrganizationDetails(), HttpStatus.OK);
    }

    @PutMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOrganization(@Valid @RequestBody UpdateOrganizationRequest organization) {
        UpdateOrganizationResponse organizationUpdate = organizationService.updateOrganization(organization);
        return new ResponseEntity<>(organizationUpdate, HttpStatus.OK);
    }
}
