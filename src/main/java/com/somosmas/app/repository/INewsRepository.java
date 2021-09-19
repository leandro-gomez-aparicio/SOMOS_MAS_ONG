package com.somosmas.app.repository;

import com.somosmas.app.model.entity.News;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface INewsRepository extends JpaRepository<News, Long> {
	
	@Transactional(readOnly = true)
    Optional<News> findByIdNews(Long idNews);

	boolean existsByName(String name);

}
