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
import com.cosmos.dtos.setups.RoleDTO;
import com.cosmos.models.setups.ERole;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.roles.IRole;

import io.swagger.annotations.Api;

@RestController
@Api("Roles Endpoints")
public class CRole {
	
	@Autowired
	private IRole sRole;

    @GetMapping(path = "/role", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "projCategory.id"));

        Page<ERole> rolePage = sRole.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched roles list", 
                    rolePage, RoleDTO.class, ERole.class));
    }
    
    @PostMapping(path = "/role", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createRole(@RequestBody RoleDTO roleDTO) 
            throws URISyntaxException {

        ERole proj = sRole.create(roleDTO);

        return ResponseEntity
            .created(new URI("/role" + proj.getId()))
            .body(new SuccessResponse(201, "Successfully created role", new RoleDTO(proj)));
    }

    @GetMapping(path = "/role/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getRoleById(@PathVariable Integer id) {

        ERole role = sRole.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched role", new RoleDTO(role)));
    }

}
