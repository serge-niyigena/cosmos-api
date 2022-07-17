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

        EProjectCategory prop = sProjectCategory.create(projectCategoryDTO);

        return ResponseEntity
            .created(new URI("/project/category" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created category", new ProjectCategoryDTO(prop)));
    }

    @GetMapping(path = "/project/category/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getProjectCategoryById(@PathVariable Integer id) {

        Optional<EProjectCategory> pprojectCategory = sProjectCategory.getById(id);
        if (!pprojectCategory.isPresent()) {
            throw new NotFoundException("category with specified id not found", "pprojectCategoryId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched pprojectCategory", new ProjectCategoryDTO(pprojectCategory.get())));
    }
    
    @GetMapping(path = "/project/category", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllProjectCategories() {

        List<EProjectCategory> pprojectCategory = sProjectCategory.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched pprojectCategory", pprojectCategory));
    }
	
}
