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

import com.cosmos.dtos.setups.ProjectStatusDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EProjectStatus;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.statuses.IProjectStatus;

import io.swagger.annotations.Api;

@RestController
@Api("Project status Endpoints")
public class CProjectStatus {

	@Autowired
	private IProjectStatus sProjectStatus;
	

    @PostMapping(path = "/project/status", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createProjectStatus(@RequestBody ProjectStatusDTO projectStatusDTO) 
            throws URISyntaxException {

        EProjectStatus prop = sProjectStatus.create(projectStatusDTO);

        return ResponseEntity
            .created(new URI("/project/status" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created status", new ProjectStatusDTO(prop)));
    }
    
    @PostMapping(path = "/project/status", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateProjectStatus(@RequestBody ProjectStatusDTO projectStatusDTO) 
            throws URISyntaxException {

        EProjectStatus prop = sProjectStatus.update(projectStatusDTO);

        return ResponseEntity
            .created(new URI("/project/status" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully updated status", new ProjectStatusDTO(prop)));
    }
    
    @PostMapping(path = "/project/status", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteProjectStatus(@RequestBody ProjectStatusDTO projectStatusDTO) 
            throws URISyntaxException {

        sProjectStatus.delete(projectStatusDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted status", projectStatusDTO));
    }

    @GetMapping(path = "/project/status/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getProjectStatusById(@PathVariable Integer id) {

        Optional<EProjectStatus> projectStatus = sProjectStatus.getById(id);
        if (!projectStatus.isPresent()) {
            throw new NotFoundException("status with specified id not found", "projectStatusId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched projectStatus", new ProjectStatusDTO(projectStatus.get())));
    }
    
    @GetMapping(path = "/project/status", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllProjectStatuses() {

        List<EProjectStatus> projectStatus = sProjectStatus.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched projectStatus", projectStatus));
    }
	
}
