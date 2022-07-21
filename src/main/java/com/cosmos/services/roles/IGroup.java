package com.cosmos.services.roles;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.GroupDTO;
import com.cosmos.models.setups.EGroup;

public interface IGroup {
	
	EGroup create(GroupDTO groupDTO);
	
    Page<EGroup> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    Optional<EGroup> getById(Integer groupId);
    
    EGroup getById(Integer groupId, Boolean throwsException);

}
