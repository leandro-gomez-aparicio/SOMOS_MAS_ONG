package com.somosmas.app.service.abstraction;

import java.util.NoSuchElementException;

import com.somosmas.app.exception.custom.OperationAccessDeniedException;
import com.somosmas.app.model.response.CommentResponse;
import com.somosmas.app.model.response.ListCommentResponse;
import com.somosmas.app.model.request.CommentRequest;

public interface ICommentService {

	    void create(CommentRequest request) throws NoSuchElementException;
        
        ListCommentResponse list();

        void delete(Long id, String authorizationHeader) throws OperationAccessDeniedException;
        
        CommentResponse update(CommentRequest comment, Long id, String authorizationHeader) throws OperationAccessDeniedException;

		ListCommentResponse getCommentsBy(Long postId);
}
