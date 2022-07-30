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
import com.cosmos.dtos.project.ProjectFloorDTO;
import com.cosmos.models.project.EProjectFloor;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.project.IProjectFloor;

import io.swagger.annotations.Api;


@RestController
@Api("Project floors Endpoints")
public class CProjectFloor {

	@Autowired
	private IProjectFloor sProjectFloor;

    @GetMapping(path = "/project/floor", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","status.id", "projCategory.id"));

        Page<EProjectFloor> projectPage = sProjectFloor.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched project floors list", 
                    projectPage, ProjectFloorDTO.class, EProjectFloor.class));
    }
    
    @PostMapping(path = "/project/floor/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createProjectFloor(@RequestBody ProjectFloorDTO projectFloorDTO) 
            throws URISyntaxException {

        EProjectFloor projFloor = sProjectFloor.create(projectFloorDTO);

        return ResponseEntity
            .created(new URI("/project/floor" + projFloor.getId()))
            .body(new SuccessResponse(201, "Successfully created project floor", new ProjectFloorDTO(projFloor)));
    }
    
    @PostMapping(path = "/project/floor/update/{floorId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateProjectFloor(@PathVariable Integer floorId,@RequestBody ProjectFloorDTO projectFloorDTO) 
            throws URISyntaxException {

        EProjectFloor projFloor = sProjectFloor.update(floorId,projectFloorDTO);

        return ResponseEntity
            .created(new URI("/project/floor" + projFloor.getId()))
            .body(new SuccessResponse(201, "Successfully updated project floor", new ProjectFloorDTO(projFloor)));
    }
    
    @PostMapping(path = "/project/floor/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteProjectFloor(@RequestBody ProjectFloorDTO projectFloorDTO) 
            throws URISyntaxException {

       sProjectFloor.delete(projectFloorDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deletd project floor", projectFloorDTO));
    }

    @GetMapping(path = "/project/floor/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getProjectFloorById(@PathVariable Integer id) {

        EProjectFloor project = sProjectFloor.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched project floor", new ProjectFloorDTO(project)));
    }
	
}
