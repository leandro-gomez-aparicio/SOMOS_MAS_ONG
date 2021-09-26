package com.somosmas.app.service;

import com.somosmas.app.exception.custom.OperationAccessDeniedException;
import com.somosmas.app.model.entity.Comment;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.CommentRequest;
import com.somosmas.app.model.response.CommentResponse;
import com.somosmas.app.model.response.ListCommentResponse;
import com.somosmas.app.repository.ICommentsRepository;
import com.somosmas.app.repository.INewsRepository;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.ICommentService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;
import com.somosmas.app.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentServiceImpl implements ICommentService {

    private static final String USER_ID_NOT_FOUND = "User ID: {0} not found.";
    private static final String NEWS_ID_NOT_FOUND = "News ID: {0} not found.";
    private static final String COMMENT_ID_NOT_FOUND = "Comment ID: {0} not found.";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    IUserRepository userRepository;

    @Autowired
    INewsRepository newsRepository;

    @Autowired
    ICommentsRepository commentRepository;

    @Autowired
    JwtUtil jwtUtil;
        
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
        comment.setTimestamp(TimestampUtil.getCurrentTime());
		commentRepository.save(comment);
	}

    @Override
    public ListCommentResponse list() {
        List<Comment> comment = commentRepository.getBodyCommentsSortByOrder();
        return buildResponse(comment);
    }

    private ListCommentResponse buildResponse(List<Comment> comment) {
        ListCommentResponse response = new ListCommentResponse();

        if (comment.isEmpty()) {
            return response;
        }

        List<CommentResponse> commentsResponses = new ArrayList<>();
        comment.stream().map(ConvertUtil::convertToDto).forEachOrdered(commentsResponses::add);

        response.setComments(commentsResponses);
        return response;
    }

    @Override
    public void delete(Long id, String authorizationHeader) throws NoSuchElementException, OperationAccessDeniedException {
        Comment comment = getComment(id);

        User user = getRequestUser(authorizationHeader);
        if (!isAdminOrCreateCommentUser(comment, user)) {
            throw new OperationAccessDeniedException("delete comment", "User is not the owner or admin user.");
        }

        commentRepository.deleteById(id);
    }

    private User getRequestUser(String authorizationHeader) {
        String userEmail = jwtUtil.extractUserEmail(authorizationHeader);
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(USER_ID_NOT_FOUND, userEmail)));
    }

    private boolean isAdminOrCreateCommentUser(Comment comment, User requestUser) {
        User commentUser = comment.getUser();
        return ROLE_ADMIN.equals(commentUser.getRole().getName())
                || commentUser.getEmail().equals(requestUser.getUsername());
    }

    @Override
    public CommentResponse update(CommentRequest commentRequest, Long id, String authorizationHeader) throws OperationAccessDeniedException {
        Comment comment = getComment(id);

        User user = getRequestUser(authorizationHeader);
        if (!isAdminOrCreateCommentUser(comment, user)) {
            throw new OperationAccessDeniedException("update comment", "User is not the owner or admin user.");
        }
        comment.setIdComment(id);
        return ConvertUtil.convertToDto(commentRepository.save(comment));

    }

    private Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(COMMENT_ID_NOT_FOUND, id)));
    }

}
