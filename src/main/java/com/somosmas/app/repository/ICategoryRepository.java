package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Optional<Category> findOneByName(String name);

    Page<Category> findBySoftDeleteIsNullOrSoftDeleteIsFalse(Pageable page);

}
