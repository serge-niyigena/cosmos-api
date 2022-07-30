package com.cosmos.services.project;

import java.util.List;
import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.ProjectFloorDTO;
import com.cosmos.models.project.EProjectFloor;

public interface IProjectFloor{

	EProjectFloor create(ProjectFloorDTO projectFloorDTO);
	
	EProjectFloor update(Integer id,ProjectFloorDTO projectFloorDTO);
	
	void delete(ProjectFloorDTO projectFloorDTO);
	
    Page<EProjectFloor> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    EProjectFloor getById(Integer projectFloorStatusId, Boolean throwException);
	
}
