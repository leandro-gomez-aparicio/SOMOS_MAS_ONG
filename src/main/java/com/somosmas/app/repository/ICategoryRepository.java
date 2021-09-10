package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
	
	@Transactional(readOnly = true)
	Optional<Category> findByIdCategory(Long idCategory);

}
