package com.cosmos.services.roles;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.RoleGroupDTO;
import com.cosmos.models.setups.ERoleGroup;

public interface IRoleGroup{
	
ERoleGroup create(RoleGroupDTO roleGroupDTO);
	
    Page<ERoleGroup> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    Optional<ERoleGroup> getById(Integer roleGroupId);
    
    ERoleGroup getById(Integer roleGroupId, Boolean throwsException);

}
