package com.cosmos.services.roles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.RoleDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.ERole;
import com.cosmos.repositories.RoleDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SRole implements IRole{
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private GlobalFunctions globalFunction;
	
	@Autowired
	private SpecFactory specFactory;
	
	 @Override
	    public Page<ERole> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
	          return roleDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public Optional<ERole> getById(Integer roleId) {
	    	
	        Optional<ERole> role = roleDAO.findById(roleId);
	        
	        return role;
	    }
	  
		@Override
		public ERole create(RoleDTO roleDTO) {
			
			ERole role = new ERole();
			role.setName(roleDTO.getRoleName());
			role.setDesc(roleDTO.getRoleDesc());
			return roleDAO.save(role);
		}
		
	

		  @SuppressWarnings("unchecked")
		    public Specification<ERole> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<ERole> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<ERole>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    
	}


		@Override
		public ERole getById(Integer roleId, Boolean throwsException) {
				
			Optional<ERole> role = roleDAO.findById(roleId);
			 
			 if(!role.isPresent() && throwsException) {
				 throw new InvalidInputException("Role with given id not found", "projectId");
		        }  
		     return role.get();
		}


		@Override
		public ERole update(RoleDTO roleDTO) {
			
			ERole role = getById(roleDTO.getRoleId(), true);
			role.setName(roleDTO.getRoleName());
			role.setDesc(roleDTO.getRoleDesc());
			
			return roleDAO.save(role);
			
		}


		@Override
		public void delete(RoleDTO roleDTO) {
			roleDAO.delete(getById(roleDTO.getRoleId(), true));
		}

}
