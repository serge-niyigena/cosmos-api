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
import com.cosmos.dtos.setups.UnitTypeDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EUnitType;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.types.IUnitType;

import io.swagger.annotations.Api;

@RestController
@Api("Unit type Endpoints")
public class CUnitType {

	@Autowired
	private IUnitType sUnitType;
	

    @PostMapping(path = "/unit/type/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createUnitType(@RequestBody UnitTypeDTO unitTypeDTO) 
            throws URISyntaxException {

        EUnitType prop = sUnitType.create(unitTypeDTO);

        return ResponseEntity
            .created(new URI("/unit/type" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created type", new UnitTypeDTO(prop)));
    }
    
    @PostMapping(path = "/unit/type/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateUnitType(@RequestBody UnitTypeDTO unitTypeDTO) 
            throws URISyntaxException {

        EUnitType prop = sUnitType.update(unitTypeDTO);

        return ResponseEntity
            .created(new URI("/unit/type" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully updated type", new UnitTypeDTO(prop)));
    }
    

    @PostMapping(path = "/unit/type/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteUnitType(@RequestBody UnitTypeDTO unitTypeDTO) 
            throws URISyntaxException {

    	sUnitType.update(unitTypeDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted type", unitTypeDTO));
    }

    @GetMapping(path = "/unit/type/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getUnitTypeById(@PathVariable Integer id) {

        Optional<EUnitType> unitType = sUnitType.getById(id);
        if (!unitType.isPresent()) {
            throw new NotFoundException("type with specified id not found", "unitTypeId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched unitType", new UnitTypeDTO(unitType.get())));
    }
    
    @GetMapping(path = "/unit/types", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "projCategory.id"));
        
        Page<EUnitType> unitType = sUnitType.getPaginatedList(pageDTO,allowableFields);
        

        return ResponseEntity
            .ok()
            .body(new SuccessPaginatedResponse(200, "Successfully fetched unitType", unitType,
            		UnitTypeDTO.class,EUnitType.class));
    }
	
}
