package com.cosmos.services.items;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.ItemDTO;
import com.cosmos.models.project.EItem;

public interface IItem {

	EItem create(ItemDTO itemDTO);
	
	EItem update(Integer id,ItemDTO itemDTO);
	
	void delete(ItemDTO itemDTO);
	
    Page<EItem> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);
    
    List<EItem> getAll();

    Optional<EItem> getById(Integer itemId);
    
    EItem getById(Integer itemId, Boolean throwsException);
	
}
