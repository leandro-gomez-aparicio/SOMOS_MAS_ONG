package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Slide;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlideRepository extends JpaRepository<Slide, Long> {
    @Query(value = "SELECT case when count(slideOrder)=0 then 0 else max(slideOrder) end from Slide")
    Integer getMaxOrder();
    boolean existsBySlideOrder(Integer slideOrder);
    List<Slide> findByOrganizationId(Long organizationId);


}
