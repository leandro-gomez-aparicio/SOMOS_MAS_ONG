package com.somosmas.app.controller;

import com.somosmas.app.model.request.MemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somosmas.app.service.abstraction.IMemberService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/members")
public class MemberController {
	@Autowired
	IMemberService memberService;
        
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody MemberRequest memberRequest){
        memberService.create(memberRequest);
        return new ResponseEntity<>(memberRequest, HttpStatus.CREATED);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMember(){
        return new ResponseEntity<>(memberService.getMembers(), HttpStatus.OK);
    }

	@DeleteMapping(value="/{idMember}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("idMember") Long idMember){
		memberService.delete(idMember);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody MemberRequest memberRequest, @PathVariable Long id){
        MemberResponse memberResponse = memberService.update(memberRequest, id);
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }
}
