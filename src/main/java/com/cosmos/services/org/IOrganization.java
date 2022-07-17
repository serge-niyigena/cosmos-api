package com.cosmos.services.org;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.OrganizationDTO;
import com.cosmos.models.setups.EOrganization;

public interface IOrganization {
	
	EOrganization create(OrganizationDTO organisationDTO);
	
    List<EOrganization> getAll();

    Optional<EOrganization> getById(Integer organizationId);

    EOrganization getById(Integer organizationId, Boolean throwException);
    
    EOrganization update(OrganizationDTO organizationDTO);  

}
