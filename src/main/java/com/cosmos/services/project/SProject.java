package com.cosmos.services.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.ProjectDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.project.EProject;
import com.cosmos.models.project.EProjectUser;
import com.cosmos.models.setups.EOrganization;
import com.cosmos.models.setups.EProjectCategory;
import com.cosmos.models.setups.EProjectStatus;
import com.cosmos.repositories.ProjectDAO;
import com.cosmos.services.categories.IProjectCategory;
import com.cosmos.services.org.IOrganization;
import com.cosmos.services.statuses.IProjectStatus;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SProject implements IProject {

	 @Autowired
	 private ProjectDAO projectDAO;

	 @Autowired
	 private IProjectCategory sProjectCategory;
	 
	 @Autowired
	 private IProjectStatus sProjectStatus;
	 
	 @Autowired
	 private IOrganization sOrganization;
	 
	 @Autowired
	 private SpecFactory specFactory;
	 
	 @Autowired
	 private GlobalFunctions globalFunction;
	 
	 @Autowired
	 private IUser sUser;
	 
	    @Override
	    public Page<EProject> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return projectDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	    
	    @Override
	    public EProject getById(Integer projectId, Boolean throwException) {
	        Optional<EProject> projectStatus = projectDAO.findById(projectId);
	        if (!projectStatus.isPresent() && throwException) {
	            throw new InvalidInputException("project with given id not found", "projectId");
	        }
	        return projectStatus.get();
	    }
	  
		@Override
		public EProject create(ProjectDTO projectDTO) {
			
			EProject project = new EProject();
			
			EProjectCategory pCat= sProjectCategory.getById(projectDTO.getCategoryId(),true);
			EProjectStatus pStat= sProjectStatus.getById(projectDTO.getStatusId(), true);
			EOrganization pOrg= sOrganization.getById(projectDTO.getOrganizationId(), true);
			
			project.setDesc(projectDTO.getDesc());
			project.setName(projectDTO.getName());
			project.setProjectWEF(projectDTO.getProjWef());
			project.setProjectWET(projectDTO.getProjWet());
			project.setReference("P"+generateRandomRef(7));
			project.setProjCreationDate(LocalDateTime.now());
			project.setProjectStatus(pStat);
			project.setProjectOrganization(pOrg);
			project.setProjCategory(pCat);
			project.setProjectItemSelectionType(projectDTO.getSelectionType());
			if(projectDTO.getUsersIds()!=null && !projectDTO.getUsersIds().isEmpty()) {
				project.setUsers(assignUsers(projectDTO.getUsersIds(),false,project));
				}
		    return projectDAO.save(project);
		}
		
		@Override
		public EProject update(Integer id,ProjectDTO projectDTO) {
			
			EProject project = getById(id, true);
			
			EProjectCategory pCat= sProjectCategory.getById(projectDTO.getCategoryId(),true);
			EProjectStatus pStat= sProjectStatus.getById(projectDTO.getStatusId(), true);
			EOrganization pOrg= sOrganization.getById(projectDTO.getOrganizationId(), true);
			
			project.setDesc(projectDTO.getDesc());
			project.setName(projectDTO.getName());
			project.setProjectWEF(projectDTO.getProjWef());
			project.setProjectWET(projectDTO.getProjWet());
			project.setProjCreationDate(LocalDateTime.now());
			project.setProjectStatus(pStat);
			project.setProjectOrganization(pOrg);
			project.setProjCategory(pCat);
			project.setProjectItemSelectionType(projectDTO.getSelectionType());
			if(projectDTO.getUsersIds()!=null && !projectDTO.getUsersIds().isEmpty()) {
			project.setUsers(assignUsers(projectDTO.getUsersIds(),true,project));
			}
			return projectDAO.save(project);
		}
		
		@Override
		public void delete(ProjectDTO projectDTO) {
			
			EProject project = getById(projectDTO.getId(), true);
			
			projectDAO.delete(project);
		}
		

		  @SuppressWarnings("unchecked")
		    public Specification<EProject> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EProject> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EProject>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }


		@Override
		public Optional<EProject> getById(Integer projectId) {
			
			 Optional<EProject> project = projectDAO.findById(projectId);
			 
		      return project;
		}
		
		public List<EProjectUser> assignUsers(List<Integer> userIds,boolean update,EProject project){
			List<EProjectUser> projectUsers = new ArrayList<>();
			if(update) {
				projectUsers= project.getUsers();
				projectUsers.clear();
				for(Integer userId: userIds) {
					
					EProjectUser projectUser = new EProjectUser();
					projectUser.setProjectUserUsers(sUser.getById(userId, true));
					projectUser.setProjectUserProject(project);
					projectUsers.add(projectUser);
				}
			}
			else {
				for(Integer userId: userIds) {
					
					EProjectUser projectUser = new EProjectUser();
					projectUser.setProjectUserUsers(sUser.getById(userId, true));
					projectUser.setProjectUserProject(project);
					projectUsers.add(projectUser);
				}
			}
			
			return projectUsers;
		}
		
		@Override
		public List<EProject> getAll() {
			
			return projectDAO.findAll();
		}
		
		public static String generateRandomRef(int len) {
			String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZOIUYTREWFVC"
	          +"LMNCIUSFTWT67881BDDHDJJYG";
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; i++)
				sb.append(chars.charAt(rnd.nextInt(chars.length())));
			return sb.toString();
		}
		
}
