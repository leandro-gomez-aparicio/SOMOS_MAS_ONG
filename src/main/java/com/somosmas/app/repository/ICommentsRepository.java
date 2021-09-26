package com.somosmas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somosmas.app.model.entity.Comment;
import com.somosmas.app.repository.custom.CommentRepositoryCustom;

@Repository
public interface ICommentsRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
	
	Comment findByIdComment(Long id);

}
