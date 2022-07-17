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
import com.cosmos.dtos.project.RoomItemDTO;
import com.cosmos.models.project.ERoomItem;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.project.IRoomItem;

import io.swagger.annotations.Api;

@RestController
@Api("Room items Endpoints")
public class CRoomItem {
	
	@Autowired
	private IRoomItem sRoomItem;

    @GetMapping(path = "/room/item", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "RoomProjectFloor.id"));

        Page<ERoomItem> roomItemPage = sRoomItem.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched room items list", 
                		roomItemPage, RoomItemDTO.class, ERoomItem.class));
    }
    
    @PostMapping(path = "/room/item", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createRoomItem(@RequestBody RoomItemDTO projectDTO) 
            throws URISyntaxException {

        ERoomItem projFloor = sRoomItem.create(projectDTO);

        return ResponseEntity
            .created(new URI("/room/item" + projFloor.getId()))
            .body(new SuccessResponse(201, "Successfully created room item", new RoomItemDTO(projFloor)));
    }

    @GetMapping(path = "room/item/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getRoomItemById(@PathVariable Integer id) {

        ERoomItem project = sRoomItem.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched room item", new RoomItemDTO(project)));
    }

}
