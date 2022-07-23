package com.cosmos.controllers.categories;

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

import com.cosmos.dtos.setups.ProjectCategoryDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EProjectCategory;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.categories.IProjectCategory;

import io.swagger.annotations.Api;

@RestController
@Api("Project category Endpoints")
public class CProjectCategory {

	@Autowired
	private IProjectCategory sProjectCategory;
	

    @PostMapping(path = "/project/category", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createProjectCategory(@RequestBody ProjectCategoryDTO projectCategoryDTO) 
            throws URISyntaxException {

        EProjectCategory pCat = sProjectCategory.create(projectCategoryDTO);

        return ResponseEntity
            .created(new URI("/project/category" + pCat.getId()))
            .body(new SuccessResponse(201, "Successfully created category", new ProjectCategoryDTO(pCat)));
    }
    
    @PostMapping(path = "/project/category", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateProjectCategory(@RequestBody ProjectCategoryDTO projectCategoryDTO) 
            throws URISyntaxException {

        EProjectCategory pCat = sProjectCategory.update(projectCategoryDTO);

        return ResponseEntity
            .created(new URI("/project/category" + pCat.getId()))
            .body(new SuccessResponse(201, "Successfully updated category", new ProjectCategoryDTO(pCat)));
    }
    
    @PostMapping(path = "/project/category", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteProjectCategory(@RequestBody ProjectCategoryDTO projectCategoryDTO) 
            throws URISyntaxException {

        sProjectCategory.delete(projectCategoryDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted category", projectCategoryDTO));
    }

    @GetMapping(path = "/project/category/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getProjectCategoryById(@PathVariable Integer id) {

        Optional<EProjectCategory> projectCategory = sProjectCategory.getById(id);
        if (!projectCategory.isPresent()) {
            throw new NotFoundException("category with specified id not found", "projectCategoryId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched pprojectCategory", new ProjectCategoryDTO(projectCategory.get())));
    }
    
    @GetMapping(path = "/project/category", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllProjectCategories() {

        List<EProjectCategory> pprojectCategory = sProjectCategory.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched pprojectCategory", pprojectCategory));
    }
	
}
