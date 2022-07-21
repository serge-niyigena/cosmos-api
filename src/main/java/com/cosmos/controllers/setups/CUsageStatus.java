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

import com.cosmos.dtos.setups.UsageStatusDTO;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.models.setups.EUsageStatus;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.statuses.IUsageStatus;

import io.swagger.annotations.Api;

@RestController
@Api("Usage status Endpoints")
public class CUsageStatus {

	@Autowired
	private IUsageStatus sUsageStatus;
	

    @PostMapping(path = "/usage/status", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createUsageStatus(@RequestBody UsageStatusDTO usageStatusDTO) 
            throws URISyntaxException {

        EUsageStatus prop = sUsageStatus.create(usageStatusDTO);

        return ResponseEntity
            .created(new URI("/usage/status" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created status", new UsageStatusDTO(prop)));
    }

    @GetMapping(path = "/usage/status/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getUsageStatusById(@PathVariable Integer id) {

        Optional<EUsageStatus> usageStatus = sUsageStatus.getById(id);
        if (!usageStatus.isPresent()) {
            throw new NotFoundException("status with specified id not found", "usageStatusId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched usageStatus", new UsageStatusDTO(usageStatus.get())));
    }
    
    @GetMapping(path = "/usage/status", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllUsageStatuses() {

        List<EUsageStatus> usageStatus = sUsageStatus.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched usageStatus", usageStatus));
    }
	
}
