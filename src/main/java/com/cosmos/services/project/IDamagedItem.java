package com.cosmos.services.project;

import java.util.List;

import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.DamagedItemDTO;
import com.cosmos.models.project.EDamagedItem;

public interface IDamagedItem {
	
	  EDamagedItem create(DamagedItemDTO projectFloorDTO);
		
	    Page<EDamagedItem> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

	    EDamagedItem getById(Integer projectFloorStatusId,Boolean throwException);

}
