package com.cosmos.services.roles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.GroupUserDTO;
import com.cosmos.models.setups.EGroupUsers;

public interface IGroupUSer {
	
    EGroupUsers create(GroupUserDTO groupDTO);
	
    Page<EGroupUsers> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    Optional<EGroupUsers> getById(Integer groupId);
    
    EGroupUsers getById(Integer groupUserId, Boolean throwsException);

}
