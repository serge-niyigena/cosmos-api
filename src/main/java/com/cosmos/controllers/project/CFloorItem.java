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
import com.cosmos.dtos.project.FloorItemDTO;
import com.cosmos.models.project.EFloorItem;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.project.IFloorItem;

import io.swagger.annotations.Api;

@RestController
@Api("Room items Endpoints")
public class CFloorItem {
	
	@Autowired
	private IFloorItem sFloorItem;

    @GetMapping(path = "/floorItem", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("id","floorItemStatus.id"));

        Page<EFloorItem> floorItemPage = sFloorItem.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched floor items list", 
                		floorItemPage, FloorItemDTO.class, EFloorItem.class));
    }
    
    @PostMapping(path = "/floorItem/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createFloorItem(@RequestBody FloorItemDTO floorItemDTO) 
            throws URISyntaxException {

        EFloorItem projFloor = sFloorItem.create(floorItemDTO);

        return ResponseEntity
            .created(new URI("/floorItem" + projFloor.getId()))
            .body(new SuccessResponse(201, "Successfully created floor item", new FloorItemDTO(projFloor)));
    }
    
    @PostMapping(path = "/floorItem/update/{itemId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateFloorItem(@PathVariable Integer itemId,@RequestBody FloorItemDTO floorItemDTO) 
            throws URISyntaxException {

        EFloorItem projFloor = sFloorItem.update(itemId,floorItemDTO);

        return ResponseEntity
            .created(new URI("/floorItem" + projFloor.getId()))
            .body(new SuccessResponse(201, "Successfully updated floor item", new FloorItemDTO(projFloor)));
    }
    
    @PostMapping(path = "/floorItem/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteFloorItem(@RequestBody FloorItemDTO floorItemDTO) 
            throws URISyntaxException {

        sFloorItem.delete(floorItemDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted floor item", floorItemDTO));
    }

    @GetMapping(path = "floorItem/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getFloorItemById(@PathVariable Integer id) {

        EFloorItem project = sFloorItem.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched floor item", new FloorItemDTO(project)));
    }

}
