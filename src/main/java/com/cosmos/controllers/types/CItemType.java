package com.cosmos.controllers.types;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cosmos.dtos.setups.ItemTypeDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EItemType;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.types.IItemType;

public class CItemType {

	@Autowired
	private IItemType sItemType;
	

    @PostMapping(path = "/item/type", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createItemType(@RequestBody ItemTypeDTO itemTypeDTO) 
            throws URISyntaxException {

        EItemType prop = sItemType.create(itemTypeDTO);

        return ResponseEntity
            .created(new URI("/item/type" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created type", new ItemTypeDTO(prop)));
    }

    @GetMapping(path = "/item/type/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getItemTypeById(@PathVariable Integer id) {

        Optional<EItemType> itemType = sItemType.getById(id);
        if (!itemType.isPresent()) {
            throw new NotFoundException("Item type with specified id not found", "itemTypeId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched itemType", new ItemTypeDTO(itemType.get())));
    }
    
    @GetMapping(path = "/item/type", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllItemTypees() {

        List<EItemType> itemType = sItemType.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched itemType", itemType));
    }
	
}
