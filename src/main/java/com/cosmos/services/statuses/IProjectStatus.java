package com.cosmos.services.statuses;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.ProjectStatusDTO;
import com.cosmos.models.setups.EProjectStatus;


public interface IProjectStatus {

	EProjectStatus create(ProjectStatusDTO projectStatusDTO);
	
    List<EProjectStatus> getAll();

    Optional<EProjectStatus> getById(Integer projectStatusId);

    EProjectStatus getById(Integer projectStatusId, Boolean throwException);
	
}
