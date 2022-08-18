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
import com.cosmos.dtos.setups.GroupDTO;
import com.cosmos.models.setups.EGroup;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.roles.IGroup;

import io.swagger.annotations.Api;

@RestController
@Api("Groups Endpoints")
public class CGroup {
	
	@Autowired
	private IGroup sGroup;

    @GetMapping(path = "/group", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","id","users.eUsers.userOrg.id"));

        Page<EGroup> groupPage = sGroup.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched groups list", 
                    groupPage, GroupDTO.class, EGroup.class,true,true));
    }
    
    @GetMapping(path = "/group/all", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllGroups() {

        List<EGroup> groups = sGroup.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched groups", groups));
    }
    
    @PostMapping(path = "/group/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createGroup(@RequestBody GroupDTO groupDTO) 
            throws URISyntaxException {

        EGroup proj = sGroup.create(groupDTO);

        return ResponseEntity
            .created(new URI("/group" + proj.getId()))
            .body(new SuccessResponse(201, "Successfully created group", new GroupDTO(proj,true,true)));
    }
    
    
    @PostMapping(path = "/group/update/{groupId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateGroup(@PathVariable Integer groupId,@RequestBody GroupDTO groupDTO) 
            throws URISyntaxException {

        EGroup proj = sGroup.update(groupId,groupDTO);

        return ResponseEntity
            .created(new URI("/group" + proj.getId()))
            .body(new SuccessResponse(201, "Successfully updated group", new GroupDTO(proj,true,true)));
    }
    
    @PostMapping(path = "/group/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteGroup(@RequestBody GroupDTO groupDTO) 
            throws URISyntaxException {

        sGroup.delete(groupDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted group", groupDTO));
    }

    @GetMapping(path = "/group/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getGroupById(@PathVariable Integer id) {

        EGroup group = sGroup.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched group", new GroupDTO(group,true,true)));
    }

}
