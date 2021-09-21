package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Long> {

    List<Member> findBySoftDeleteIsNullOrSoftDeleteIsFalse();
}
