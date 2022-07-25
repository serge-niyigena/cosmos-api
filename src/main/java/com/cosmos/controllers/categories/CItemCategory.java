package com.cosmos.controllers.categories;

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
import org.springframework.web.bind.annotation.RestController;

import com.cosmos.dtos.setups.ItemCategoryDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EItemCategory;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.categories.IItemCategory;

import io.swagger.annotations.Api;

@RestController
@Api("Item category Endpoints")
public class CItemCategory {

	@Autowired
	private IItemCategory sItemCategory;
	

    @PostMapping(path = "/item/category/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createItemCategory(@RequestBody ItemCategoryDTO itemCategoryDTO) 
            throws URISyntaxException {

        EItemCategory prop = sItemCategory.create(itemCategoryDTO);

        return ResponseEntity
            .created(new URI("/item/category" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created category", new ItemCategoryDTO(prop)));
    }
    
    @PostMapping(path = "/item/category/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteItemCategory(@RequestBody ItemCategoryDTO itemCategoryDTO) 
            throws URISyntaxException {

    	sItemCategory.delete(itemCategoryDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully deleted category", itemCategoryDTO));
    }
    
    @PostMapping(path = "/item/category/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateItemCategory(@RequestBody ItemCategoryDTO itemCategoryDTO) 
            throws URISyntaxException {

        EItemCategory prop = sItemCategory.update(itemCategoryDTO);

        return ResponseEntity
            .created(new URI("/item/category" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully updated category", new ItemCategoryDTO(prop)));
    }

    @GetMapping(path = "/item/category/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getItemCategoryById(@PathVariable Integer id) {

        Optional<EItemCategory> itemCategory = sItemCategory.getById(id);
        if (!itemCategory.isPresent()) {
            throw new NotFoundException("category with specified id not found", "itemCategoryId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched itemCategory", new ItemCategoryDTO(itemCategory.get())));
    }
    
    @GetMapping(path = "/item/category", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllItemCategories() {

        List<EItemCategory> itemCategory = sItemCategory.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched itemCategory", itemCategory));
    }
	
}
