package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.project.EProject;

public interface ProjectDAO extends JpaRepository<EProject, Integer>, JpaSpecificationExecutor<EProject> {

}
