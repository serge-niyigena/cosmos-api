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
import com.cosmos.dtos.setups.GroupUserDTO;
import com.cosmos.models.setups.EGroupUsers;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.roles.IGroupUSer;

import io.swagger.annotations.Api;

@RestController
@Api("Group users Endpoints")
public class CGroupUser {
	
	@Autowired
	private IGroupUSer sGroupUser;

    @GetMapping(path = "/groupUser", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "projCategory.id"));

        Page<EGroupUsers> groupUserPage = sGroupUser.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched groupUsers list", 
                    groupUserPage, GroupUserDTO.class, EGroupUsers.class));
    }
    
    @PostMapping(path = "/groupUser/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createGroupUser(@RequestBody GroupUserDTO groupUserDTO) 
            throws URISyntaxException {

        EGroupUsers groupUsers = sGroupUser.create(groupUserDTO);

        return ResponseEntity
            .created(new URI("/groupUser" + groupUsers.getId()))
            .body(new SuccessResponse(201, "Successfully created groupUser", groupUsers));
    }
    
    @PostMapping(path = "/groupUser/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateGroupUser(@RequestBody GroupUserDTO groupUserDTO) 
            throws URISyntaxException {

        EGroupUsers groupUsers = sGroupUser.update(groupUserDTO);

        return ResponseEntity
            .created(new URI("/groupUser" + groupUsers.getId()))
            .body(new SuccessResponse(201, "Successfully updated groupUser",groupUsers));
    }
    
    @PostMapping(path = "/groupUser/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteGroupUser(@RequestBody GroupUserDTO groupUserDTO) 
            throws URISyntaxException {

    	sGroupUser.delete(groupUserDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted groupUser", groupUserDTO));
    }

    @GetMapping(path = "/groupUser/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getGroupUserById(@PathVariable Integer id) {

        EGroupUsers groupUser = sGroupUser.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched groupUser", groupUser));
    }

}
