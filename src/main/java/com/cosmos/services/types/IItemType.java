package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.ItemTypeDTO;
import com.cosmos.models.setups.EItemType;

public interface IItemType {

    EItemType create(ItemTypeDTO itemTypeDTO);
	
    List<EItemType> getAll();

    Optional<EItemType> getById(Integer itemTypeId);

    EItemType getById(Integer itemTypeId, Boolean throwException);
	
}
