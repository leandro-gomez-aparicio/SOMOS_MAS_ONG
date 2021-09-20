package com.somosmas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somosmas.app.service.abstraction.IMemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	@Autowired
	IMemberService memberService;

	@DeleteMapping(value="/{idMember}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("idMember") Long idMember){
		memberService.delete(idMember);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}
	
}
