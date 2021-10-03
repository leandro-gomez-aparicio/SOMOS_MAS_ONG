package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Comment;
import com.somosmas.app.repository.custom.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentsRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    @Query(value = "SELECT * FROM comment WHERE news_id = ?1", nativeQuery = true)
    List<Comment> findBy(Long postId);

}
