package com.cosmos.services.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.UserDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.project.EProjectUser;
import com.cosmos.models.setups.EGroupUsers;
import com.cosmos.models.setups.EUser;
import com.cosmos.models.setups.EUserType;
import com.cosmos.repositories.UserDAO;
import com.cosmos.services.roles.IGroup;
import com.cosmos.services.types.IUserType;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SUser implements IUser {
	
	@Autowired
	private GlobalFunctions globalFunction;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private IUserType sUserType;
	
	@Autowired
	private IGroup sGroup;
	
	@Autowired
	private IProject sProject;
	
	@Autowired
	private SpecFactory specFactory;
	
	  @Override
	    public Page<EUser> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return userDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	    
	    @Override
	    public EUser getById(Integer userId, Boolean throwException) {
	        Optional<EUser> userStatus = userDAO.findById(userId);
	        if (!userStatus.isPresent() && throwException) {
	            throw new InvalidInputException("user with given id not found", "userId");
	        }
	        return userStatus.get();
	    }
	  
		@Override
		public EUser create(UserDTO userDTO) {
			
			EUser user = new EUser();
			
			EUserType uType= sUserType.getById(userDTO.getUserTypeId(),true);
			
			user.setUserFullName(userDTO.getUserFullName());
			user.setUserPassword(new BCryptPasswordEncoder().encode(userDTO.getUserPassword()));
			user.setUserEmail(userDTO.getUserEmail());
			user.setUserMobile(userDTO.getUserMobile());
			user.setUserReset(userDTO.getUserReset());
			user.setUserActive(userDTO.getUserStatus());
			user.setEUserType(uType);
			if(userDTO.getGroupsIds()!=null && !userDTO.getGroupsIds().isEmpty()) {
			user.setGroups(assignGroups(userDTO.getGroupsIds(),false,user));
			}
			if(userDTO.getProjectsIds()!=null && !userDTO.getProjectsIds().isEmpty()) {
			user.setProjects(assignProjects(userDTO.getProjectsIds(),false,user));
			}
			
			return userDAO.save(user);
		}
		
		@Override
		public EUser update(Integer userId,UserDTO userDTO) {
			
			EUser user = getById(userId, true);
			
			EUserType uType= sUserType.getById(userDTO.getUserTypeId(),true);
			
			user.setUserFullName(userDTO.getUserFullName());
			user.setUserEmail(userDTO.getUserEmail());
			user.setUserMobile(userDTO.getUserMobile());
			user.setUserReset(userDTO.getUserReset());
			user.setUserActive(userDTO.getUserStatus());
			user.setEUserType(uType);
			if(userDTO.getGroupsIds()!=null && !userDTO.getGroupsIds().isEmpty()) {
				user.setGroups(assignGroups(userDTO.getGroupsIds(),true,user));
				}
				if(userDTO.getProjectsIds()!=null && !userDTO.getProjectsIds().isEmpty() ) {
				user.setProjects(assignProjects(userDTO.getProjectsIds(),true,user));
				}
			
			return userDAO.save(user);
		}
		
		@Override
		public void delete(UserDTO userDTO) {
			
			EUser user = getById(userDTO.getId(), true);
		
			userDAO.delete(user);
		}
		
		  @SuppressWarnings("unchecked")
		    public Specification<EUser> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EUser> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EUser>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }


		@Override
		public Optional<EUser> getById(Integer userId) {
			
			 Optional<EUser> user = userDAO.findById(userId);
			 
		      return user;
		}


		@Override
		public Optional<EUser> getByMobileOrEmail(String contact) {
			
			return userDAO.findMobileOrEmail(contact);
		}
		
		public List<EGroupUsers> assignGroups(List<Integer> groupsIds, boolean update,EUser user){
			List<EGroupUsers> userGroups = new ArrayList<>();
			if(update) {
				userGroups= user.getGroups();
				userGroups.clear();
				for(Integer groupId: groupsIds) {
					
					EGroupUsers groupUser = new EGroupUsers();
					groupUser.setEUsers(user);
					groupUser.setEGroup(sGroup.getById(groupId, true));
					userGroups.add(groupUser);
				}
				
			}
			else {
				for(Integer groupId: groupsIds) {
					
					EGroupUsers groupUser = new EGroupUsers();
					groupUser.setEUsers(user);
					groupUser.setEGroup(sGroup.getById(groupId, true));
					userGroups.add(groupUser);
				}
				
			}
			return userGroups;
		}
		
		public List<EProjectUser> assignProjects(List<Integer> projectsIds,boolean update,EUser user){
			List<EProjectUser> userProjects = new ArrayList<>();
			
			if(update) {
				userProjects=user.getProjects();
				userProjects.clear();
				for(Integer projectId: projectsIds) {
					
					EProjectUser projUser = new EProjectUser();
					projUser.setProjectUserUsers(user);
					projUser.setProjectUserProject(sProject.getById(projectId, true));
					userProjects.add(projUser);
				
				}
			}
			
			return userProjects;
		}

}
