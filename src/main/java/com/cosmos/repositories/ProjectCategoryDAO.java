package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.setups.EProjectCategory;

public interface ProjectCategoryDAO extends JpaRepository<EProjectCategory, Integer>,JpaSpecificationExecutor<EProjectCategory>{

}
