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
import com.cosmos.dtos.project.ProjectDTO;
import com.cosmos.models.project.EProject;
import com.cosmos.models.setups.EGroup;
import com.cosmos.responses.SuccessPaginatedResponse;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.project.IProject;

import io.swagger.annotations.Api;

@RestController
@Api("Project Endpoints")
public class CProject {
	
	@Autowired
	private IProject sProject;

    @GetMapping(path = "/project", produces = "application/json")
    public ResponseEntity<SuccessPaginatedResponse> getList(@RequestParam(required = false) Map<String, Object> params) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PageDTO pageDTO = new PageDTO(params);

        List<String> allowableFields = new ArrayList<String>(
                Arrays.asList("name","reference","projectStatus.id", "projCategory.id","users.projectUserUsers.id","projectOrganization.id"));

        Page<EProject> projectPage = sProject.getPaginatedList(pageDTO, allowableFields);
        
        return ResponseEntity
                .ok()
                .body(new SuccessPaginatedResponse(200, "Successfully fetched projects list", 
                    projectPage, ProjectDTO.class, EProject.class,true,true));
    }
    
    @PostMapping(path = "/project/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createProject(@RequestBody ProjectDTO projectDTO) 
            throws URISyntaxException {

        EProject proj = sProject.create(projectDTO);

        return ResponseEntity
            .created(new URI("/project" + proj.getId()))
            .body(new SuccessResponse(201, "Successfully created project", new ProjectDTO(proj,true,true)));
    }
    
    @PostMapping(path = "/project/update/{projectId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateProject(@PathVariable Integer projectId,@RequestBody ProjectDTO projectDTO) 
            throws URISyntaxException {

        EProject proj = sProject.update(projectId,projectDTO);

        return ResponseEntity
            .created(new URI("/project" + proj.getId()))
            .body(new SuccessResponse(201, "Successfully updated project", new ProjectDTO(proj,true,true)));
    }
    
    @PostMapping(path = "/project/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteProject(@RequestBody ProjectDTO projectDTO) 
            throws URISyntaxException {

        sProject.delete(projectDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted project", projectDTO));
    }


    @GetMapping(path = "/project/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getProjectById(@PathVariable Integer id) {

        EProject project = sProject.getById(id,true);
       
        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched project", new ProjectDTO(project,true,true)));
    }
    
    

    @GetMapping(path = "/project/all", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllProjects() {

        List<EProject> groups = sProject.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched groups", groups));
    }
	
	
}
