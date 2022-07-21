package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.project.EProjectUser;

public interface ProjectUserDAO extends JpaRepository<EProjectUser, Integer>, JpaSpecificationExecutor<EProjectUser> {

}
