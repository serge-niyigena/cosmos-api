package com.cosmos.controllers.categories;

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
import com.cosmos.dtos.setups.ItemCategoryDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EItemCategory;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.categories.IItemCategory;
import io.swagger.annotations.Api;


@RestController
@Api("Item category Endpoints")
public class CItemCategory {

	@Autowired
	private IItemCategory sItemCategory;
	

	@GetMapping(path = "/itemCategory", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name"));

        Page<EItemCategory> groupPage = sItemCategory.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched categories list", 
                    groupPage, ItemCategoryDTO.class, EItemCategory.class));
    }
	
    @PostMapping(path = "/itemCategory/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createItemCategory(@RequestBody ItemCategoryDTO itemCategoryDTO) 
            throws URISyntaxException {

        EItemCategory prop = sItemCategory.create(itemCategoryDTO);

        return ResponseEntity
            .created(new URI("/itemCategory" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created category", new ItemCategoryDTO(prop)));
    }
    
    @PostMapping(path = "/itemCategory/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteItemCategory(@RequestBody ItemCategoryDTO itemCategoryDTO) 
            throws URISyntaxException {

    	sItemCategory.delete(itemCategoryDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully deleted category", itemCategoryDTO));
    }
    
    @PostMapping(path = "/itemCategory/update/{catId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateItemCategory(@RequestBody ItemCategoryDTO itemCategoryDTO,@PathVariable Integer catId) 
            throws URISyntaxException {

        EItemCategory prop = sItemCategory.update(catId,itemCategoryDTO);

        return ResponseEntity
            .created(new URI("/itemCategory" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully updated category", new ItemCategoryDTO(prop)));
    }

    @GetMapping(path = "/itemCategory/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getItemCategoryById(@PathVariable Integer id) {

        Optional<EItemCategory> itemCategory = sItemCategory.getById(id);
        if (!itemCategory.isPresent()) {
            throw new NotFoundException("category with specified id not found", "itemCategoryId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched itemCategory", new ItemCategoryDTO(itemCategory.get())));
    }
    
    @GetMapping(path = "/itemCategory/all", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllItemCategories() {

        List<EItemCategory> itemCategory = sItemCategory.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched itemCategory", itemCategory));
    }
	
}
