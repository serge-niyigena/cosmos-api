package com.cosmos.services.project;

import java.util.List;
import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.FloorItemDTO;
import com.cosmos.models.project.EFloorItem;

public interface IFloorItem {

    EFloorItem create(FloorItemDTO projectFloorDTO);
    
    EFloorItem update(Integer id,FloorItemDTO projectFloorDTO);
    
    void delete(FloorItemDTO projectFloorDTO);
	
    Page<EFloorItem> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    EFloorItem getById(Integer projectFloorStatusId,Boolean throwException);
	
}
