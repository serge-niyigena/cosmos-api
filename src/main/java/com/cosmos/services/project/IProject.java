package com.cosmos.services.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.ProjectDTO;
import com.cosmos.models.project.EProject;

public interface IProject {

	EProject create(ProjectDTO projectDTO);
	
	EProject update(Integer id,ProjectDTO projectDTO);
	
	void delete(ProjectDTO projectDTO);
	
    Page<EProject> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);
    
    Optional<EProject> getById(Integer floorRoomStatusId);

    EProject getById(Integer projectStatusId,Boolean throwException);
	
}
