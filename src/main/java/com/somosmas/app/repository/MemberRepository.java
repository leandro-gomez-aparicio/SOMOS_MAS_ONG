package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member, Long> {

}