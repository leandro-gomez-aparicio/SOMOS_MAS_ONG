package com.somosmas.app.controller;

import com.somosmas.app.exception.custom.NewsAlreadyExistException;
import com.somosmas.app.model.request.CreateNewsRequest;
import com.somosmas.app.model.request.NewsRequest;
import com.somosmas.app.model.response.NewsResponse;
import com.somosmas.app.service.abstraction.ICommentService;
import com.somosmas.app.service.abstraction.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class NewsController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private ICommentService commentService;

    @DeleteMapping(value = "/news/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        newsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/news/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findBy(@PathVariable Long id) {
        return new ResponseEntity<>(newsService.findBy(id), HttpStatus.OK);
    }

    @PutMapping(value = "/news/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody NewsRequest news, @PathVariable Long id) {
        NewsResponse newResponse = newsService.update(news, id);
        return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateNewsRequest newRequest) throws NewsAlreadyExistException {
        newsService.create(newRequest);
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }

    @GetMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNews(@RequestParam("page") int page, UriComponentsBuilder uriBuilder) {
        return new ResponseEntity<>(newsService.getNews(page, uriBuilder), HttpStatus.OK);
    }

    @GetMapping(value = "/posts/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCommentsByPost(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getCommentsBy(id), HttpStatus.OK);
    }
}
