package com.cosmos.services.categories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.ItemCategoryDTO;
import com.cosmos.models.setups.EItemCategory;


public interface IItemCategory {

	EItemCategory create(ItemCategoryDTO usagestatusDTO);
	
	EItemCategory update(Integer categoryId,ItemCategoryDTO usagestatusDTO);
	
	 Page<EItemCategory> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);
	
	void delete(ItemCategoryDTO usagestatusDTO);
	
    List<EItemCategory> getAll();

    Optional<EItemCategory> getById(Integer itemCategoryId);

    EItemCategory getById(Integer itemCategoryId, Boolean throwException);
	
}
