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
import org.springframework.web.bind.annotation.RestController;

import com.cosmos.dtos.setups.UnitTypeDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EUnitType;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.types.IUnitType;

import io.swagger.annotations.Api;

@RestController
@Api("Unit type Endpoints")
public class CUnitType {

	@Autowired
	private IUnitType sUnitType;
	

    @PostMapping(path = "/unit/type", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createUnitType(@RequestBody UnitTypeDTO unitTypeDTO) 
            throws URISyntaxException {

        EUnitType prop = sUnitType.create(unitTypeDTO);

        return ResponseEntity
            .created(new URI("/unit/type" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created type", new UnitTypeDTO(prop)));
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
    
    @GetMapping(path = "/unit/type", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllUnitTypees() {

        List<EUnitType> unitType = sUnitType.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched unitType", unitType));
    }
	
}
