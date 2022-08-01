package com.cosmos.controllers.setups;

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
import com.cosmos.dtos.setups.ItemTypeDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EItemType;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.types.IItemType;

import io.swagger.annotations.Api;

@RestController
@Api("Item types Endpoints")
public class CItemType {

	@Autowired
	private IItemType sItemType;
	

    @PostMapping(path = "/itemType/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createItemType(@RequestBody ItemTypeDTO itemTypeDTO) 
            throws URISyntaxException {

        EItemType prop = sItemType.create(itemTypeDTO);

        return ResponseEntity
            .created(new URI("/itemType" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created type", new ItemTypeDTO(prop)));
    }
    

    @PostMapping(path = "/itemType/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateItemType(@RequestBody ItemTypeDTO itemTypeDTO) 
            throws URISyntaxException {

        EItemType prop = sItemType.update(itemTypeDTO);

        return ResponseEntity
            .created(new URI("/itemType" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully updated type", new ItemTypeDTO(prop)));
    }
    
    @PostMapping(path = "/itemType/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteItemType(@RequestBody ItemTypeDTO itemTypeDTO) 
            throws URISyntaxException {

      sItemType.delete(itemTypeDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted type",itemTypeDTO));
    }

    @GetMapping(path = "/itemType/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getItemTypeById(@PathVariable Integer id) {

        Optional<EItemType> itemType = sItemType.getById(id);
        if (!itemType.isPresent()) {
            throw new NotFoundException("Item type with specified id not found", "itemTypeId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched itemType", new ItemTypeDTO(itemType.get())));
    }
    
    @GetMapping(path = "/itemType", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name"));

        Page<EItemType> itemType = sItemType.getPaginatedList(pageDTO,allowableFields);
        

        return ResponseEntity
            .ok()
            .body(new SuccessPaginatedResponse(200, "Successfully fetched itemType",
            		itemType,ItemTypeDTO.class,EItemType.class));
    }
	
}
