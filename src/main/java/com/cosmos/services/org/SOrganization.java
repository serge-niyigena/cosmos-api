package com.cosmos.services.org;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.OrganizationDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EOrganization;
import com.cosmos.repositories.OrganizationDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SOrganization implements IOrganization {

	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	@Autowired
	private GlobalFunctions globalFunction;
	
	@Autowired
	private SpecFactory specFactory;
	
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
	public void delete(Integer orgId) {
	     
		organizationDAO.delete(getById(orgId, true));
	}

	@Override
	public EOrganization update(OrganizationDTO organizationDTO,Integer orgId) {
		
		EOrganization org = getById(orgId, true);
		
		org.setName(organizationDTO.getName());
		org.setPhysicalAddress(organizationDTO.getPhysicalAddress());
		org.setPostalAddress(organizationDTO.getPostalAddress());
		org.setMobileNumber(organizationDTO.getMobileNumber());
		org.setEmail(organizationDTO.getEmail());
		
		return organizationDAO.save(org);
	}


	@Override
	public Page<EOrganization> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
		String searchQuery = pageDTO.getSearch();

        PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
        return organizationDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	}
	
	 @SuppressWarnings("unchecked")
	    public Specification<EOrganization> buildFilterSpec(String searchQuery, List<String> allowedFields) {

	        SpecBuilder<EOrganization> specBuilder = new SpecBuilder<>();

	        specBuilder = (SpecBuilder<EOrganization>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

	        return specBuilder.build();
	    }


	@Override
	public List<EOrganization> getAll() {
		
		return organizationDAO.findAll();
	}

}
