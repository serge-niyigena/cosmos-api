package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.models.setups.EUserTypes;


public interface IUserType {

	EUserTypes create(UserTypeDTO userTypeDTO);
	
    List<EUserTypes> getAll();

    Optional<EUserTypes> getById(Integer userTypeId);

    EUserTypes getById(Integer userTypeId, Boolean throwException);
	
}
