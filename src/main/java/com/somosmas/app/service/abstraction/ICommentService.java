package com.somosmas.app.service.abstraction;

import java.util.NoSuchElementException;

import com.somosmas.app.model.request.CommentRequest;

public interface ICommentService {

	void create(CommentRequest request) throws NoSuchElementException;

}
