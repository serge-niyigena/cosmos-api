package com.cosmos.services.roles;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.RoleDTO;
import com.cosmos.models.setups.ERole;

public interface IRole {
	
	
	ERole create(RoleDTO roleDTO);
	
	ERole update(RoleDTO roleDTO);
	
	void delete(RoleDTO roleDTO);
	
	 List<ERole> getAll();
	
    Page<ERole> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    Optional<ERole> getById(Integer roleId);
    
    ERole getById(Integer roleId, Boolean throwsException);

}
