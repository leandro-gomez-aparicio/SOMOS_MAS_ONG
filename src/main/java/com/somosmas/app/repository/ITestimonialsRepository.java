package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Testimonials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestimonialsRepository extends JpaRepository<Testimonials, Long> {

    Page<Testimonials> findBySoftDeleteIsNullOrSoftDeleteIsFalse(Pageable page);
}
