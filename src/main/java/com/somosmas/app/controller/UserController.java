package com.somosmas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.service.abstraction.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list(){
		return new ResponseEntity<>(userService.listActiveUsers(),HttpStatus.OK);
	}
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("id") Long idUser) {
		userService.delete(idUser);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("id") Long idUser, @RequestBody UserDetailsRequest userRequest){
		return new ResponseEntity<>(userService.update(idUser, userRequest), HttpStatus.OK);
	}
}
