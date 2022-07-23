package com.cosmos.services.categories;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.ItemCategoryDTO;
import com.cosmos.models.setups.EItemCategory;


public interface IItemCategory {

	EItemCategory create(ItemCategoryDTO usagestatusDTO);
	
	EItemCategory update(ItemCategoryDTO usagestatusDTO);
	
	void delete(ItemCategoryDTO usagestatusDTO);
	
    List<EItemCategory> getAll();

    Optional<EItemCategory> getById(Integer itemCategoryId);

    EItemCategory getById(Integer itemCategoryId, Boolean throwException);
	
}
