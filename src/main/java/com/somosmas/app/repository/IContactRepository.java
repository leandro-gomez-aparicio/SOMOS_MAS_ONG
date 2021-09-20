package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByEmail(String email);

    List<Contact> findBySoftDeleteIsNullOrSoftDeleteIsFalse();

}
