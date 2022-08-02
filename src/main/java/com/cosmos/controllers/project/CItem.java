package com.cosmos.controllers.project;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.cosmos.dtos.project.ItemDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.project.EItem;
import com.cosmos.models.setups.EGroup;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.items.IItem;

import io.swagger.annotations.Api;

@RestController
@Api("Items Endpoints")
public class CItem {
	
	@Autowired
	private IItem sItem;

    @GetMapping(path = "/item", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) throws
    InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
    NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","itemCategory.id", "itemUnitType.id","itemType.id"));

        Page<EItem> itemPage = sItem.getPaginatedList(pageDTO, allowableFields);

        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched items list", 
                		itemPage, ItemDTO.class, EItem.class));
    }
    
    @PostMapping(path = "/item/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createItem(@RequestBody ItemDTO itemDTO) 
            throws URISyntaxException {

        EItem item = sItem.create(itemDTO);

        return ResponseEntity
            .created(new URI("/item" + item.getId()))
            .body(new SuccessResponse(201, "Successfully created item", new ItemDTO(item)));
    }
    
    @PostMapping(path = "/item/update/{itemId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateItem(@PathVariable Integer itemId,@RequestBody ItemDTO itemDTO) 
            throws URISyntaxException {

        EItem item = sItem.update(itemId,itemDTO);

        return ResponseEntity
            .created(new URI("/item" + item.getId()))
            .body(new SuccessResponse(201, "Successfully updated item", new ItemDTO(item)));
    }
    
    @PostMapping(path = "/item/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteItem(@RequestBody ItemDTO itemDTO) 
            throws URISyntaxException {

         sItem.delete(itemDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted item", itemDTO));
    }

    @GetMapping(path = "/item/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getItemById(@PathVariable Integer id) {

        Optional<EItem> item = sItem.getById(id);
        if (!item.isPresent()) {
            throw new NotFoundException("item with specified id not found", "itemId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched item", new ItemDTO(item.get())));
    }
    
    @GetMapping(path = "/item/all", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllGroups() {

        List<EItem> groups = sItem.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched items", groups));
    }
	
	
}
