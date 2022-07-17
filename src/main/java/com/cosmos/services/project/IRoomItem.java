package com.cosmos.services.project;

import java.util.List;
import org.springframework.data.domain.Page;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.RoomItemDTO;
import com.cosmos.models.project.ERoomItem;

public interface IRoomItem {

    ERoomItem create(RoomItemDTO projectFloorDTO);
	
    Page<ERoomItem> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    ERoomItem getById(Integer projectFloorStatusId,Boolean throwException);
	
}
