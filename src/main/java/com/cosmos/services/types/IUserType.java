package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.models.setups.EUserType;


public interface IUserType {

	EUserType create(UserTypeDTO userTypeDTO);
	
	EUserType update(UserTypeDTO userTypeDTO);
	
	void delete(UserTypeDTO userTypeDTO);
	
    List<EUserType> getAll();

    Optional<EUserType> getById(Integer userTypeId);

    EUserType getById(Integer userTypeId, Boolean throwException);
	
}
