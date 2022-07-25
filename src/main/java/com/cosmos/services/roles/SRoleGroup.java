package com.cosmos.services.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.RoleGroupDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EGroup;
import com.cosmos.models.setups.ERole;
import com.cosmos.models.setups.ERoleGroup;
import com.cosmos.repositories.RoleGroupDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SRoleGroup implements IRoleGroup{
	
	@Autowired
	private RoleGroupDAO roleGroupDAO;
	
	@Autowired
	private GlobalFunctions globalFunction;
	
	@Autowired
	private SpecFactory specFactory;
	
	@Autowired
	private IGroup sGroup;
	
	@Autowired
	private IRole sRole;
	
	 @Override
	    public Page<ERoleGroup> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
	          return roleGroupDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public Optional<ERoleGroup> getById(Integer roleGroupId) {
	    	
	        Optional<ERoleGroup> roleGroup = roleGroupDAO.findById(roleGroupId);
	        
	        return roleGroup;
	    }
	  
		@Override
		public ERoleGroup create(RoleGroupDTO roleGroupDTO) {
			
			ERoleGroup roleGroup = new ERoleGroup();
			
			EGroup group = sGroup.getById(roleGroupDTO.getGroupId(), true);
			
			ERole role = sRole.getById(roleGroupDTO.getRoleId(), true);
			
			roleGroup.setGroup(group);
			roleGroup.setRole(role);
			
			return roleGroupDAO.save(roleGroup);
		}
		
		
		  @SuppressWarnings("unchecked")
		    public Specification<ERoleGroup> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<ERoleGroup> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<ERoleGroup>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    
	}


		@Override
		public ERoleGroup getById(Integer roleGroupId, Boolean throwsException) {
				
			Optional<ERoleGroup> roleGroup = roleGroupDAO.findById(roleGroupId);
			 
			 if(!roleGroup.isPresent() && throwsException) {
				 throw new InvalidInputException("RoleGroup with given id not found", "projectId");
		        }  
		     return roleGroup.get();
		}
		
		public List<ERole> setRoles(List<Integer> RolesIds){
			
			List<ERole> rolesList = new ArrayList<ERole>();
			
			for(Integer roleId: RolesIds) {
				rolesList.add(sRole.getById(roleId, true));
			}
			
			return rolesList;
		}


		@Override
		public ERoleGroup update(RoleGroupDTO roleGroupDTO) {
			
			ERoleGroup roleGroup = getById(roleGroupDTO.getRoleGroupId(), true);
			
//			EGroup group = sGroup.getById(roleGroupDTO.getRoleGroupGroupId(), true);
//			
//			roleGroup.setEGroup(group);
//			roleGroup.setERoles(setRoles(roleGroupDTO.getRoleGroupRolesId()));
			
			return roleGroupDAO.save(roleGroup);
			
		}

		@Override
		public void delete(RoleGroupDTO roleGroupDTO) {
			roleGroupDAO.delete(getById(roleGroupDTO.getRoleGroupId(), true));	
		}


}
