package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    @Transactional(readOnly = true)
    Optional<Category> findByIdCategory(Long idCategory);

	Optional<Category> findOneByName(String name);

}
