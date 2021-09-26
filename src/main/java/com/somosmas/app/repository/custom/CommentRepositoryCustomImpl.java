package com.somosmas.app.repository.custom;

import com.somosmas.app.model.entity.Comment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{
    
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Comment> getBodyCommentsSortByOrder() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> commentCriteria = criteriaQuery.from(Comment.class);

        Order order = criteriaBuilder.desc(commentCriteria.get("timestamp"));
        Selection selection = criteriaBuilder.construct(Comment.class, commentCriteria.get("body"));

        criteriaQuery.select(selection)
                .orderBy(order);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
