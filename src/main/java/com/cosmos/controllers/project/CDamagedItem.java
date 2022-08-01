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
import com.cosmos.dtos.project.DamagedItemDTO;
import com.cosmos.models.project.EDamagedItem;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.project.IDamagedItem;

import io.swagger.annotations.Api;

@RestController
@Api("Damaged items Endpoints")
public class CDamagedItem {
	@Autowired
	private IDamagedItem sDamagedItem;

    @GetMapping(path = "/damagedItem", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "RoomProjectFloor.id"));

        Page<EDamagedItem> damagedItemPage = sDamagedItem.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched damaged items list", 
                		damagedItemPage, DamagedItemDTO.class, EDamagedItem.class));
    }
    
    @PostMapping(path = "/damagedItem/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createDamagedItem(@RequestBody DamagedItemDTO damagedItemDTO) 
            throws URISyntaxException {

        EDamagedItem damagedItem = sDamagedItem.create(damagedItemDTO);

        return ResponseEntity
            .created(new URI("/damagedItem" + damagedItem.getId()))
            .body(new SuccessResponse(201, "Successfully created damaged item", new DamagedItemDTO(damagedItem)));
    }
    
    @PostMapping(path = "/damagedItem/update/{itemId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateDamagedItem(@PathVariable Integer itemId,@RequestBody DamagedItemDTO damagedItemDTO) 
            throws URISyntaxException {

        EDamagedItem damagedItem = sDamagedItem.update(itemId,damagedItemDTO);

        return ResponseEntity
            .created(new URI("/damagedItem" + damagedItem.getId()))
            .body(new SuccessResponse(201, "Successfully updated damaged item", new DamagedItemDTO(damagedItem)));
    }
    
    @PostMapping(path = "/damagedItem/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteDamagedItem(@RequestBody DamagedItemDTO damagedItemDTO) 
            throws URISyntaxException {

       sDamagedItem.delete(damagedItemDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted damaged item", damagedItemDTO));
    }

    @GetMapping(path = "damagedItem/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getDamagedItemById(@PathVariable Integer id) {

        EDamagedItem project = sDamagedItem.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched damaged item", new DamagedItemDTO(project)));
    }


}
