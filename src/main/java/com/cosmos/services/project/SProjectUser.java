package com.cosmos.services.project;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.ProjectUserDTO;
import com.cosmos.models.project.EProject;
import com.cosmos.models.project.EProjectUser;
import com.cosmos.models.setups.EUser;
import com.cosmos.repositories.ProjectUserDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SProjectUser implements IProjectUser {
	
	@Autowired
	 private ProjectUserDAO projectDAO;

	 @Autowired
	 private IProject sProjectUserProject; 
	 
	 @Autowired
	 private SpecFactory specFactory;
	 
	 @Autowired
	 private GlobalFunctions globalFunction;
	 
	 @Autowired
	 private IUser sUser;
	 
	    @Override
	    public Page<EProjectUser> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return projectDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public EProjectUser getById(Integer floorId,Boolean throwException) {
	    	
	        EProjectUser projectUser = projectDAO.getById(floorId);
	        
	        return projectUser;
	    }
	  
		@Override
		public EProjectUser create(ProjectUserDTO projectUserDTO) {
			
			EProjectUser projectUser = new EProjectUser();
			

			EProject proj= sProjectUserProject.getById(projectUserDTO.getProjectUserProjectId(),true);
			EUser user= sUser.getById(projectUserDTO.getProjectUserUserId(),true);
			
			projectUser.setProjectUserProject(proj);
			projectUser.setProjectUserUsers(user);
			
			return projectDAO.save(projectUser);
		}
		
		@Override
		public EProjectUser update(ProjectUserDTO projectUserDTO) {
			
			EProjectUser projectUser = getById(projectUserDTO.getProjectUserId(), true);
			
			EProject proj= sProjectUserProject.getById(projectUserDTO.getProjectUserProjectId(),true);
			EUser user= sUser.getById(projectUserDTO.getProjectUserUserId(),true);
			
			projectUser.setProjectUserProject(proj);
			projectUser.setProjectUserUsers(user);
			
			return projectDAO.save(projectUser);
		}
		
		@Override
		public void delete(ProjectUserDTO projectUserDTO) {
			
			EProjectUser projectUser = getById(projectUserDTO.getProjectUserId(), true);
			
			projectDAO.delete(projectUser);
		}

		  @SuppressWarnings("unchecked")
		    public Specification<EProjectUser> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EProjectUser> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EProjectUser>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }
		  
		  public List<EUser> setUsers(List<Integer> usersIds){
			  List<EUser> usersList = new ArrayList<EUser>();
			  
			  for(Integer userId: usersIds) {
				  usersList.add(sUser.getById(userId, true));
			  }
			  
			  return usersList;
		  }

}
