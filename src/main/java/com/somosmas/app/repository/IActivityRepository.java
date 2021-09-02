package com.somosmas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somosmas.app.model.entity.Activity;


@Repository
public interface IActivityRepository extends JpaRepository<Activity, Long>{

}
