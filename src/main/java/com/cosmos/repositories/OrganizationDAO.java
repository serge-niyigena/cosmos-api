package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.models.setups.EOrganization;

public interface OrganizationDAO extends JpaRepository<EOrganization, Integer>{

}
