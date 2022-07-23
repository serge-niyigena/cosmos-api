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
import com.cosmos.dtos.setups.GroupUserDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EGroup;
import com.cosmos.models.setups.EGroupUsers;
import com.cosmos.models.setups.EUser;
import com.cosmos.repositories.GroupUserDAO;
import com.cosmos.services.project.IUser;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SGroupUser implements IGroupUSer {
	
	@Autowired
	private GlobalFunctions globalFunction;
	
	@Autowired
	private GroupUserDAO groupUserDAO;
	
	@Autowired
	private IGroup sGroup;
	
	@Autowired
	private IUser sUser;
	
	@Autowired
	private SpecFactory specFactory;
	
    @Override
    public Page<EGroupUsers> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
    	  String searchQuery = pageDTO.getSearch();

          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
          return groupUserDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
    }

   
    @Override
    public Optional<EGroupUsers> getById(Integer groupUserId) {
    	
        Optional<EGroupUsers> groupUser = groupUserDAO.findById(groupUserId);
        
        return groupUser;
    }
  
	@Override
	public EGroupUsers create(GroupUserDTO groupUserDTO) {
		
		EGroupUsers groupUser = new EGroupUsers();
		
		EGroup group = sGroup.getById(groupUserDTO.getGroupUserId(), true);
		
		groupUser.setEGroup(group);
		groupUser.setEUsers(setUsers(groupUserDTO.getUserIds()));
		
		return groupUserDAO.save(groupUser);
	}
	

	  @SuppressWarnings("unchecked")
	    public Specification<EGroupUsers> buildFilterSpec(String searchQuery, List<String> allowedFields) {

	        SpecBuilder<EGroupUsers> specBuilder = new SpecBuilder<>();

	        specBuilder = (SpecBuilder<EGroupUsers>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

	        return specBuilder.build();
	    
}


	@Override
	public EGroupUsers getById(Integer groupUserId, Boolean throwsException) {
			
		Optional<EGroupUsers> groupUser = groupUserDAO.findById(groupUserId);
		 
		 if(!groupUser.isPresent() && throwsException) {
			 throw new InvalidInputException("GroupUserss with given id not found", "projectId");
	        }  
	     return groupUser.get();
	}
	
	public List<EUser> setUsers(List<Integer> userIds){
		List<EUser> usersList = new ArrayList<EUser>();
		
		for(Integer userId: userIds) {
			usersList.add(sUser.getById(userId,true));
		}
		
		return usersList;
	}


	@Override
	public EGroupUsers update(GroupUserDTO groupUserDTO) {
		
		EGroupUsers groupUser = getById(groupUserDTO.getGroupUserId(), true);
		
		EGroup group = sGroup.getById(groupUserDTO.getGroupUserId(), true);
		
		groupUser.setEGroup(group);
		groupUser.setEUsers(setUsers(groupUserDTO.getUserIds()));
		
		return groupUserDAO.save(groupUser);
	}


	@Override
	public void delete(GroupUserDTO groupUserDTO) {
		
		groupUserDAO.delete(getById(groupUserDTO.getGroupUserId(), true));
	}
	


}
