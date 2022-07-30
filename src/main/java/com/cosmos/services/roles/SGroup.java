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
import com.cosmos.dtos.setups.GroupDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EGroup;
import com.cosmos.models.setups.EGroupUsers;
import com.cosmos.models.setups.ERoleGroup;
import com.cosmos.repositories.GroupDAO;
import com.cosmos.services.project.IUser;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SGroup implements IGroup {
	
	@Autowired
	private GlobalFunctions globalFunction;
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private SpecFactory specFactory;
	
	@Autowired
	private IRole sRole;
	
	@Autowired
	private IUser sUser;
	
    @Override
    public Page<EGroup> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
    	  String searchQuery = pageDTO.getSearch();

          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
          return groupDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
    }

   
    @Override
    public Optional<EGroup> getById(Integer groupId) {
    	
        Optional<EGroup> group = groupDAO.findById(groupId);
        
        return group;
    }
  
	@Override
	public EGroup create(GroupDTO groupDTO) {
		
		EGroup group = new EGroup();
		group.setDesc(groupDTO.getDesc());
		group.setName(groupDTO.getName());
		if(groupDTO.getRolesIds()!=null && !groupDTO.getRolesIds().isEmpty()) {
			group.setRoles(assignRoles(groupDTO.getRolesIds(), false,group));
		}
		
		if(groupDTO.getUsersIds()!=null && !groupDTO.getUsersIds().isEmpty()) {
			group.setUsers(assignUsers(groupDTO.getUsersIds(),false, group));
		}
		
		return groupDAO.save(group);
	}
	

	  @SuppressWarnings("unchecked")
	    public Specification<EGroup> buildFilterSpec(String searchQuery, List<String> allowedFields) {

	        SpecBuilder<EGroup> specBuilder = new SpecBuilder<>();

	        specBuilder = (SpecBuilder<EGroup>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

	        return specBuilder.build();
	    
}


	@Override
	public EGroup getById(Integer groupId, Boolean throwsException) {
			
		Optional<EGroup> group = groupDAO.findById(groupId);
		 
		 if(!group.isPresent() && throwsException) {
			 throw new InvalidInputException("Group with given id not found", "projectId");
	        }  
	     return group.get();
	}


	@Override
	public EGroup update(Integer id,GroupDTO groupDTO) {
		
		EGroup group = getById(id, true);
		group.setDesc(groupDTO.getDesc());
		group.setName(groupDTO.getName());
		
		if(groupDTO.getRolesIds()!=null && !groupDTO.getRolesIds().isEmpty()) {
			group.setRoles(assignRoles(groupDTO.getRolesIds(),true, group));
		}
		
		if(groupDTO.getUsersIds()!=null && !groupDTO.getUsersIds().isEmpty()) {
			group.setUsers(assignUsers(groupDTO.getUsersIds(), true,group));
		}
		
		return groupDAO.save(group);
	}


	@Override
	public void delete(GroupDTO groupDTO) {
		
		groupDAO.delete(getById(groupDTO.getId(), true));
		
	}
	
	
	public List<EGroupUsers> assignUsers(List<Integer> userIds,boolean update,EGroup group){
		List<EGroupUsers> userGroups = new ArrayList<>();
		if(update) {
			userGroups= group.getUsers();
			userGroups.clear();
			// group.getRoles().clear();
			 //group.getUsers().clear();
			for(Integer userId: userIds) {
				
				EGroupUsers groupUser = new EGroupUsers();
				groupUser.setEUsers(sUser.getById(userId, true));
				groupUser.setEGroup(group);
				userGroups.add(groupUser);
			}
		}
		else{
		for(Integer userId: userIds) {
	
			EGroupUsers groupUser = new EGroupUsers();
			groupUser.setEUsers(sUser.getById(userId, true));
			groupUser.setEGroup(group);
			userGroups.add(groupUser);
		}
		}
		return userGroups;
	}
	
	public List<ERoleGroup> assignRoles(List<Integer> rolesIds,boolean update,EGroup group){
		
		List<ERoleGroup> groupRoles = new ArrayList<>();
			
		if(update) {
			groupRoles = group.getRoles();
			groupRoles.clear();
			for(Integer roleId: rolesIds) {
				ERoleGroup groupRole = new ERoleGroup();
				groupRole.setGroup(group);
				groupRole.setRole(sRole.getById(roleId, true));
				groupRoles.add(groupRole);
			}
			
		}
		else {
			for(Integer roleId: rolesIds) {
				ERoleGroup groupRole = new ERoleGroup();
				groupRole.setGroup(group);
				groupRole.setRole(sRole.getById(roleId, true));
				groupRoles.add(groupRole);
			}
			
		}
		return groupRoles;
	}


	@Override
	public List<EGroup> getAll() {
		return groupDAO.findAll();
	}

}
