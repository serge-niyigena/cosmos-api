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
import com.cosmos.dtos.setups.RoleGroupDTO;
import com.cosmos.models.setups.ERoleGroup;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.roles.IRoleGroup;

import io.swagger.annotations.Api;

@RestController
@Api("Role Group Endpoints")
public class CRoleGroup {
	
	@Autowired
	private IRoleGroup sRoleGroup;

    @GetMapping(path = "/roleGroup", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "projCategory.id"));

        Page<ERoleGroup> roleGroupPage = sRoleGroup.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched roleGroups list", 
                    roleGroupPage, RoleGroupDTO.class, ERoleGroup.class));
    }
    
    @PostMapping(path = "/roleGroup/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createRoleGroup(@RequestBody RoleGroupDTO roleGroupDTO) 
            throws URISyntaxException {

        ERoleGroup proj = sRoleGroup.create(roleGroupDTO);

        return ResponseEntity
            .created(new URI("/roleGroup" + proj.getId()))
            .body(new SuccessResponse(201, "Successfully created roleGroup", new RoleGroupDTO(proj)));
    }
    
    @PostMapping(path = "/roleGroup/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateRoleGroup(@RequestBody RoleGroupDTO roleGroupDTO) 
            throws URISyntaxException {

        ERoleGroup proj = sRoleGroup.update(roleGroupDTO);

        return ResponseEntity
            .created(new URI("/roleGroup" + proj.getId()))
            .body(new SuccessResponse(201, "Successfully updated roleGroup", new RoleGroupDTO(proj)));
    }
    
    @PostMapping(path = "/roleGroup/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteRoleGroup(@RequestBody RoleGroupDTO roleGroupDTO) 
            throws URISyntaxException {

     sRoleGroup.delete(roleGroupDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted roleGroup", roleGroupDTO));
    }

    @GetMapping(path = "/roleGroup/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getRoleGroupById(@PathVariable Integer id) {

        ERoleGroup roleGroup = sRoleGroup.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched roleGroup", new RoleGroupDTO(roleGroup)));
    }

}
