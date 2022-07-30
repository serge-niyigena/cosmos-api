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
	

    @PostMapping(path = "/usageStatus/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> createUsageStatus(@RequestBody UsageStatusDTO usageStatusDTO) 
            throws URISyntaxException {

        EUsageStatus prop = sUsageStatus.create(usageStatusDTO);

        return ResponseEntity
            .created(new URI("/usageStatus" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully created status", new UsageStatusDTO(prop)));
    }
    
    @PostMapping(path = "/usageStatus/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> updateUsageStatus(@RequestBody UsageStatusDTO usageStatusDTO) 
            throws URISyntaxException {

        EUsageStatus prop = sUsageStatus.update(usageStatusDTO);

        return ResponseEntity
            .created(new URI("/usageStatus" + prop.getId()))
            .body(new SuccessResponse(201, "Successfully updated status", new UsageStatusDTO(prop)));
    }
    
    @PostMapping(path = "/usageStatus/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteUsageStatus(@RequestBody UsageStatusDTO usageStatusDTO) 
            throws URISyntaxException {

       sUsageStatus.delete(usageStatusDTO);

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(201, "Successfully deleted status",usageStatusDTO));
    }

    @GetMapping(path = "/usageStatus/{id}", produces = "application/json")
    public ResponseEntity<SuccessResponse> getUsageStatusById(@PathVariable Integer id) {

        Optional<EUsageStatus> usageStatus = sUsageStatus.getById(id);
        if (!usageStatus.isPresent()) {
            throw new NotFoundException("status with specified id not found", "usageStatusId");
        }

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched usageStatus", new UsageStatusDTO(usageStatus.get())));
    }
    
    @GetMapping(path = "/usageStatus", produces = "application/json")
    public ResponseEntity<SuccessResponse> getAllUsageStatuses() {

        List<EUsageStatus> usageStatus = sUsageStatus.getAll();
        

        return ResponseEntity
            .ok()
            .body(new SuccessResponse(200, "Successfully fetched usageStatus", usageStatus));
    }
	
}
