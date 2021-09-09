package com.somosmas.app.controller;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.request.UpdateOrganizationRequest;
import com.somosmas.app.model.response.UpdateOrganizationResponse;
import com.somosmas.app.service.abstraction.IOrganizationService;
import com.somosmas.app.util.ConvertUtil;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> updateOrganization(@Valid @RequestBody UpdateOrganizationRequest organizacion) {
		UpdateOrganizationResponse organizacionUpdate= organizationService.updateOrganization(organizacion);
		return new ResponseEntity<>(organizacionUpdate, HttpStatus.CREATED);

	}
}
