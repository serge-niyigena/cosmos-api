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

import com.cosmos.dtos.setups.OrganizationDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EOrganization;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.org.IOrganization;

import io.swagger.annotations.Api;

@RestController
@Api("Organization Endpoints")
public class COrganization {

	@Autowired
	private IOrganization sOrganization;
	

    @PostMapping(path = "/organization", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createOrganization(@RequestBody OrganizationDTO organizationDTO) 
            throws URISyntaxException {

        EOrganization org = sOrganization.create(organizationDTO);

        return ResponseEntity
            .created(new URI("/organization" + org.getId()))
            .body(new SuccessResponse(201, "Successfully created organization", new OrganizationDTO(org)));
    }
    
    @PostMapping(path = "/organization/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateOrganization(@RequestBody OrganizationDTO organizationDTO) 
            throws URISyntaxException {

        EOrganization org = sOrganization.update(organizationDTO);

        return ResponseEntity
            .created(new URI("/organization" + org.getId()))
            .body(new SuccessResponse(201, "Successfully updated organization", new OrganizationDTO(org)));
    }

    @GetMapping(path = "/organization/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getOrganizationById(@PathVariable Integer id) {

        Optional<EOrganization> organization = sOrganization.getById(id);
        if (!organization.isPresent()) {
            throw new NotFoundException("organization with specified id not found", "organizationId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched organization", new OrganizationDTO(organization.get())));
    }
    
    @GetMapping(path = "/organization", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllOrganizations() {

        List<EOrganization> organization = sOrganization.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched organization", organization));
    }
	
}
