package com.cosmos.services.categories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.ProjectCategoryDTO;
import com.cosmos.models.setups.EProjectCategory;


public interface IProjectCategory {

	EProjectCategory create(ProjectCategoryDTO projectCategoryDTO);
	
	EProjectCategory update(Integer id,ProjectCategoryDTO projectCategoryDTO);
	
	void delete(ProjectCategoryDTO projectCategoryDTO);
	
	Page<EProjectCategory> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);
	
    List<EProjectCategory> getAll();

    Optional<EProjectCategory> getById(Integer projectCategoryId);

    EProjectCategory getById(Integer projectCategoryId, Boolean throwException);
	
}
