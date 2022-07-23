package com.cosmos.services.project;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.FloorRoomDTO;
import com.cosmos.models.project.EFloorRoom;


public interface IFloorRoom {

    EFloorRoom create(FloorRoomDTO floorRoomDTO);
    
    EFloorRoom update(FloorRoomDTO floorRoomDTO);
    
    void delete(FloorRoomDTO floorRoomDTO);
	
    Page<EFloorRoom> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    Optional<EFloorRoom> getById(Integer floorRoomStatusId);
    
    EFloorRoom getById(Integer floorRoomStatusId, Boolean throwsException);
	
}
