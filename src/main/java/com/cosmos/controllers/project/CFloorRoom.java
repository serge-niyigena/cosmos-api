package com.cosmos.controllers.project;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.FloorRoomDTO;
import com.cosmos.models.project.EFloorRoom;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.project.IFloorRoom;

import io.swagger.annotations.Api;

@RestController
@Api("Floor rooms Endpoints")
public class CFloorRoom {
	
	@Autowired
	private IFloorRoom sFloorRoom;

    @GetMapping(path = "/floor/room", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "RoomProjectFloor.id"));

        Page<EFloorRoom> projectPage = sFloorRoom.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched floor room list", 
                    projectPage, FloorRoomDTO.class, EFloorRoom.class));
    }
    
    @PostMapping(path = "/floor/room", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createFloorRoom(@RequestBody FloorRoomDTO floorRoomDTO) 
            throws URISyntaxException {

        EFloorRoom projFloor = sFloorRoom.create(floorRoomDTO);

        return ResponseEntity
            .created(new URI("/floor/room" + projFloor.getId()))
            .body(new SuccessResponse(201, "Successfully created floor room", new FloorRoomDTO(projFloor)));
    }
    
    @PostMapping(path = "/floor/room", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateFloorRoom(@RequestBody FloorRoomDTO floorRoomDTO) 
            throws URISyntaxException {

        EFloorRoom projFloor = sFloorRoom.update(floorRoomDTO);

        return ResponseEntity
            .created(new URI("/floor/room" + projFloor.getId()))
            .body(new SuccessResponse(201, "Successfully updated floor room", new FloorRoomDTO(projFloor)));
    }
    
    @PostMapping(path = "/floor/room", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteFloorRoom(@RequestBody FloorRoomDTO floorRoomDTO) 
            throws URISyntaxException {

        sFloorRoom.delete(floorRoomDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted floor room", floorRoomDTO));
    }

    @GetMapping(path = "floor/room/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getFloorRoomById(@PathVariable Integer id) {

        EFloorRoom project = sFloorRoom.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully floor room", new FloorRoomDTO(project)));
    }

}
