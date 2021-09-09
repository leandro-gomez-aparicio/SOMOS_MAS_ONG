package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact,Long> {
}
