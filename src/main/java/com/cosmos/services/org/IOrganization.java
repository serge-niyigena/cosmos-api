package com.cosmos.services.org;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.OrganizationDTO;
import com.cosmos.models.setups.EOrganization;

public interface IOrganization {
	
	EOrganization create(OrganizationDTO organisationDTO);
	
    Page<EOrganization> getPaginatedList(PageDTO pageDTO, List<String> allowedFields);

    Optional<EOrganization> getById(Integer organizationId);

    EOrganization getById(Integer organizationId, Boolean throwException);
    
    EOrganization update(OrganizationDTO orgDTO, Integer orgId);  
    
    void delete(Integer orgId);

}
