package com.cosmos.services.categories;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.ProjectCategoryDTO;
import com.cosmos.models.setups.EProjectCategory;


public interface IProjectCategory {

	EProjectCategory create(ProjectCategoryDTO projectCategoryDTO);
	
	EProjectCategory update(ProjectCategoryDTO projectCategoryDTO);
	
	void delete(ProjectCategoryDTO projectCategoryDTO);
	
    List<EProjectCategory> getAll();

    Optional<EProjectCategory> getById(Integer projectCategoryId);

    EProjectCategory getById(Integer projectCategoryId, Boolean throwException);
	
}
