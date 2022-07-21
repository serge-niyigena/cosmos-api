package com.cosmos.controllers.setups;

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

import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EUserType;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.types.IUserType;

import io.swagger.annotations.Api;

@RestController
@Api("User type Endpoints")
public class CUserType {

	@Autowired
	private IUserType sUserType;
	

    @PostMapping(path = "/user/type", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createUserType(@RequestBody UserTypeDTO userTypeDTO) 
            throws URISyntaxException {

        EUserType prop = sUserType.create(userTypeDTO);

        return ResponseEntity
            .created(new URI("/user/type" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created type", new UserTypeDTO(prop)));
    }

    @GetMapping(path = "/user/type/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getUserTypeById(@PathVariable Integer id) {

        Optional<EUserType> userType = sUserType.getById(id);
        if (!userType.isPresent()) {
            throw new NotFoundException("type with specified id not found", "userTypeId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched userType", new UserTypeDTO(userType.get())));
    }
    
    @GetMapping(path = "/user/type", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllUserTypees() {

        List<EUserType> userType = sUserType.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched userType", userType));
    }
	
}
