package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Long> {

    Page<Member> findBySoftDeleteIsNullOrSoftDeleteIsFalse(Pageable page);
}
