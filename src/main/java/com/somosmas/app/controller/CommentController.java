package com.somosmas.app.controller;

import com.somosmas.app.exception.custom.OperationAccessDeniedException;
import com.somosmas.app.model.request.CommentRequest;
import com.somosmas.app.model.response.CommentResponse;
import com.somosmas.app.service.abstraction.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	@PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@Valid @RequestBody CommentRequest commentRequest){
		commentService.create(commentRequest);
		return new ResponseEntity<>(commentRequest, HttpStatus.CREATED);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		return new ResponseEntity<>(commentService.list(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id,
									@RequestHeader(name = "Authorization", required = true) String authorizationHeader)
			throws OperationAccessDeniedException {
		commentService.delete(id, authorizationHeader);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody CommentRequest comment,
									@PathVariable Long id,
									@RequestHeader(name = "Authorization", required = true) String authorizationHeader)
			throws OperationAccessDeniedException {
		CommentResponse commentResponse = commentService.update(comment, id, authorizationHeader);
    	return new ResponseEntity<>(comment, HttpStatus.OK);
	}
	
}

