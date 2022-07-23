package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.UnitTypeDTO;
import com.cosmos.models.setups.EUnitType;


public interface IUnitType {

	EUnitType create(UnitTypeDTO unitTypeDTO);
	
	EUnitType update(UnitTypeDTO unitTypeDTO);
	
	void delete(Integer unitTypeId);
	
    Page<EUnitType> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    Optional<EUnitType> getById(Integer unitTypeId);

    EUnitType getById(Integer unitTypeId, Boolean throwException);
	
}
