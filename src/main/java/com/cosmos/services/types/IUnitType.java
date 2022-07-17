package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.UnitTypeDTO;
import com.cosmos.models.setups.EUnitType;


public interface IUnitType {

	EUnitType create(UnitTypeDTO unitTypeDTO);
	
    List<EUnitType> getAll();

    Optional<EUnitType> getById(Integer unitTypeId);

    EUnitType getById(Integer unitTypeId, Boolean throwException);
	
}
