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
import com.cosmos.dtos.project.ProjectUserDTO;
import com.cosmos.models.project.EProjectUser;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.project.IProjectUser;

import io.swagger.annotations.Api;

@RestController
@Api("Project users Endpoints")
public class CProjectUser {
	
	@Autowired
	private IProjectUser sProjectUser;

    @GetMapping(path = "/projectUser", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "projCategory.id"));

        Page<EProjectUser> projectUserPage = sProjectUser.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched projectUsers list", 
                    projectUserPage, ProjectUserDTO.class, EProjectUser.class));
    }
    
    @PostMapping(path = "/projectUser/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createProjectUser(@RequestBody ProjectUserDTO projectUserDTO) 
            throws URISyntaxException {

        EProjectUser projectUsers = sProjectUser.create(projectUserDTO);

        return ResponseEntity
        		.created(new URI("/groupUser" + projectUsers.getId()))
                .body(new SuccessResponse(201, "Successfully created projectUser", new ProjectUserDTO(projectUsers)));
    }
    
    @PostMapping(path = "/projectUser/update/{pUserId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateProjectUser(@PathVariable Integer pUserId,@RequestBody ProjectUserDTO projectUserDTO) 
            throws URISyntaxException {

        EProjectUser projectUsers = sProjectUser.update(pUserId,projectUserDTO);

        return ResponseEntity
        		.created(new URI("/groupUser" + projectUsers.getId()))
                .body(new SuccessResponse(201, "Successfully updated projectUser", new ProjectUserDTO(projectUsers)));
    }
    
    @PostMapping(path = "/projectUser/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteProjectUser(@RequestBody ProjectUserDTO projectUserDTO) 
            throws URISyntaxException {

        sProjectUser.delete(projectUserDTO);

        return ResponseEntity
        		.ok()
                .body(new SuccessResponse(201, "Successfully updated projectUser",projectUserDTO));
    }

    @GetMapping(path = "/projectUser/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getProjectUserById(@PathVariable Integer id) {

        EProjectUser projectUser = sProjectUser.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched projectUser", new ProjectUserDTO(projectUser)));
    }


}
