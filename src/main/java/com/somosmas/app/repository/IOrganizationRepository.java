package com.somosmas.app.repository;

import com.somosmas.app.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganizationRepository extends JpaRepository<Organization, Long> {

}
