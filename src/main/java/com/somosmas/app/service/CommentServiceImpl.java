package com.somosmas.app.service;

import com.somosmas.app.model.entity.Comment;
import com.somosmas.app.model.request.CommentRequest;
import com.somosmas.app.model.response.CommentResponse;
import com.somosmas.app.model.response.ListCommentResponse;
import com.somosmas.app.repository.ICommentsRepository;
import com.somosmas.app.repository.INewsRepository;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.ICommentService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        
        @PersistenceContext
        EntityManager entityManager;
	
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
        List<Comment> comment = getBodyCommentsSortByOrder();
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

    // TODO: this should be in the implementation repository
    private List<Comment> getBodyCommentsSortByOrder() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> commentCriteria = criteriaQuery.from(Comment.class);

        Order order = criteriaBuilder.desc(commentCriteria.get("timestamp"));

        criteriaQuery.select(commentCriteria.get("body"))
                .orderBy(order);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
