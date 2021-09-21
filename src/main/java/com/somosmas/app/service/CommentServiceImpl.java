package com.somosmas.app.service;

import com.somosmas.app.model.entity.Comment;
import com.somosmas.app.model.request.CommentRequest;
import com.somosmas.app.repository.ICommentsRepository;
import com.somosmas.app.repository.INewsRepository;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.ICommentService;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {
	
	private static final String USER_ID_NOT_FOUND = "User ID: {0} not found.";
	private static final String NEWS_ID_NOT_FOUND = "News ID: {0} not found.";
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	INewsRepository newsRepository;
	
	@Autowired
	ICommentsRepository commentRepository;
	
	@Override
	public void create(CommentRequest request) throws NoSuchElementException{
		userRepository.findById(request.getIdUser()).orElseThrow(() ->
        new NoSuchElementException(MessageFormat.format(USER_ID_NOT_FOUND, request.getIdUser())));
		
		newsRepository.findById(request.getIdPost()).orElseThrow(() ->
		new NoSuchElementException(MessageFormat.format(NEWS_ID_NOT_FOUND, request.getIdPost())));
		
		Comment comment = new Comment();
		comment.setUser(userRepository.getById(request.getIdUser()));
		comment.setBody(request.getBody());
		comment.setNews(newsRepository.getById(request.getIdPost()));
		commentRepository.save(comment);
	}

}
