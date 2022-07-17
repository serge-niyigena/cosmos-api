package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.models.setups.EProjectCategory;

public interface ProjectCategoryDAO extends JpaRepository<EProjectCategory, Integer>{

}
