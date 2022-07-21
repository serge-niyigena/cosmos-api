package com.cosmos.services.project;

import java.util.List;
import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.ProjectUserDTO;
import com.cosmos.models.project.EProjectUser;

public interface IProjectUser {

	
	EProjectUser create(ProjectUserDTO projectUserDTO);
	
    Page<EProjectUser> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    EProjectUser getById(Integer projectUserId, Boolean throwException);
	
}
