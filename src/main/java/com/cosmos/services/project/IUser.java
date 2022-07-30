package com.cosmos.services.project;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.UserDTO;
import com.cosmos.models.setups.EUser;
public interface IUser {

    EUser create(UserDTO userDTO);
    
    EUser update(Integer id,UserDTO userDTO);
    
    void delete(UserDTO userDTO);
	
    Page<EUser> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);
    
    Optional<EUser> getById(Integer userId);
    
    Optional<EUser> getByMobileOrEmail(String contact);

    EUser getById(Integer userId,Boolean throwException);
	
}
