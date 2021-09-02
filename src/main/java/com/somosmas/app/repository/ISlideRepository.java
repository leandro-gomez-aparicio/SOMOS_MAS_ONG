package com.somosmas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somosmas.app.model.entity.Slide;

@Repository
public interface ISlideRepository extends JpaRepository<Slide, Long>{

}
