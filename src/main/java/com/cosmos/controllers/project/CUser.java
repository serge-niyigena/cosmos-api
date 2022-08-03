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
import com.cosmos.dtos.project.UserDTO;
import com.cosmos.models.setups.ERole;
import com.cosmos.models.setups.EUser;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.project.IUser;

import io.swagger.annotations.Api;

@RestController
@Api("User Endpoints")
public class CUser {
	
	@Autowired
	private IUser sUser;
	
	 @GetMapping(path = "/user/all", produces = "application/json")
	    public ResponseEntity<SuccessResponse> getAllUsers() {

	        List<EUser> users = sUser.getAll();
	        

	        return ResponseEntity
	            .ok()
	            .body(new SuccessResponse(200, "Successfully fetched users", users));
	    }

    @GetMapping(path = "/user", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("userFullName","userMobile","userEmail","userOrg.id","groups.eGroup.id","userType.id"));

        Page<EUser> userPage = sUser.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched users list", 
                    userPage, UserDTO.class, EUser.class,true,true));
    }
    
    @PostMapping(path = "/user/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createUser(@RequestBody UserDTO userDTO) 
            throws URISyntaxException {

        EUser user = sUser.create(userDTO);

        return ResponseEntity
            .created(new URI("/user" + user.getId()))
            .body(new SuccessResponse(201, "Successfully created user", new UserDTO(user,true,true)));
    }
    
    @PostMapping(path = "/user/update/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateUser(@PathVariable Integer userId,@RequestBody UserDTO userDTO) 
            throws URISyntaxException {

        EUser user = sUser.update(userId,userDTO);

        return ResponseEntity
            .created(new URI("/user" + user.getId()))
            .body(new SuccessResponse(201, "Successfully updated user", new UserDTO(user,true,true)));
    }
    
    @PostMapping(path = "/user/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteUser(@RequestBody UserDTO userDTO) 
              {

       sUser.delete(userDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted user",userDTO));
    }

    @GetMapping(path = "/user/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getUserById(@PathVariable Integer id) {

        EUser user = sUser.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched user", new UserDTO(user,true,true)));
    }

}
