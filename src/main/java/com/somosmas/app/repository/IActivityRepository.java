package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IActivityRepository extends JpaRepository<Activity, Long> {

	boolean existsByName(String name);

}
