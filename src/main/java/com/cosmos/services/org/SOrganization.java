package com.cosmos.services.org;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.OrganizationDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EOrganization;
import com.cosmos.repositories.OrganizationDAO;

@Service
public class SOrganization implements IOrganization {

	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	@Override
	public EOrganization create(OrganizationDTO organizationDTO) {
		
		EOrganization org = new EOrganization();
		
		org.setName(organizationDTO.getName());
		org.setPhysicalAddress(organizationDTO.getPhysicalAddress());
		org.setPostalAddress(organizationDTO.getPostalAddress());
		org.setMobileNumber(organizationDTO.getMobileNumber());
		org.setEmail(organizationDTO.getEmail());
		
		return organizationDAO.save(org);
	}

	@Override
	public List<EOrganization> getAll() {
		 return organizationDAO.findAll();
	}

	@Override
	public Optional<EOrganization> getById(Integer organizationId) {
		return organizationDAO.findById(organizationId);
	}

	@Override
	public EOrganization getById(Integer organizationId, Boolean throwException) {
		 Optional<EOrganization> projectCategory = organizationDAO.findById(organizationId);
	        if (!projectCategory.isPresent() && throwException) {
	            throw new InvalidInputException("Organization with given id not found", "organizationId");
	        }
	        return projectCategory.get();
	}

	@Override
	public EOrganization update(OrganizationDTO organizationDTO) {
		Optional<EOrganization> org = organizationDAO.findById(organizationDTO.getId());
		
		if (!org.isPresent()) {
            throw new InvalidInputException("Organization with given id not found", "organizationId");
        }
		else {
		
		org.get().setName(organizationDTO.getName());
		org.get().setPhysicalAddress(organizationDTO.getPhysicalAddress());
		org.get().setPostalAddress(organizationDTO.getPostalAddress());
		org.get().setMobileNumber(organizationDTO.getMobileNumber());
		org.get().setEmail(organizationDTO.getEmail());
		}
		return organizationDAO.save(org.get());
	}

}
