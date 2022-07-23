package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.ItemTypeDTO;
import com.cosmos.models.setups.EItemType;

public interface IItemType {

    EItemType create(ItemTypeDTO itemTypeDTO);
    
    EItemType update(ItemTypeDTO itemTypeDTO);
    
    void delete(ItemTypeDTO itemTypeDTO);
	
    Page<EItemType> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    Optional<EItemType> getById(Integer itemTypeId);

    EItemType getById(Integer itemTypeId, Boolean throwException);
	
}
