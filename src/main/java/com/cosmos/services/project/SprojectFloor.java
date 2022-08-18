package com.cosmos.services.project;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.ProjectFloorDTO;
import com.cosmos.models.project.EProject;
import com.cosmos.models.project.EProjectFloor;
import com.cosmos.models.setups.EProjectStatus;
import com.cosmos.repositories.ProjectFloorDAO;
import com.cosmos.services.statuses.IProjectStatus;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SprojectFloor implements IProjectFloor {

	 @Autowired
	 private ProjectFloorDAO pFloorDAO;

	 @Autowired
	 private IProject sProjectFloorProject;
	 
	 @Autowired
	 private IProjectStatus sProjectFloorStatus;
	 
	 
	 @Autowired
	 private SpecFactory specFactory;
	 
	 @Autowired
	 private GlobalFunctions globalFunction;
	 
	    @Override
	    public Page<EProjectFloor> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return pFloorDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public EProjectFloor getById(Integer floorId,Boolean throwException) {
	    	
	        EProjectFloor projectFloor = pFloorDAO.getById(floorId);
	        
	        return projectFloor;
	    }
	  
		@Override
		public EProjectFloor create(ProjectFloorDTO projectFloorDTO) {
			
			EProjectFloor project = new EProjectFloor();
			
			EProject proj= sProjectFloorProject.getById(projectFloorDTO.getProjectId(),true);
			EProjectStatus pStat= sProjectFloorStatus.getById(projectFloorDTO.getStatusId(), true);
			
			project.setProjectFloorDescription(projectFloorDTO.getFloorDescription());
			project.setProjectFloorValue(projectFloorDTO.getFloorValue());
			project.setProjectFloorRef(projectFloorDTO.getFloorRef());
			project.setProjectFloorMeasurement(projectFloorDTO.getFloorMeasurement());
			project.setProjectFloorProject(proj);
			project.setProjectFloorStatus(pStat);
			
			return pFloorDAO.save(project);
		}
		
		@Override
		public EProjectFloor update(Integer id,ProjectFloorDTO projectFloorDTO) {
			
			EProjectFloor project = getById(id, true);
			
			EProject proj= sProjectFloorProject.getById(projectFloorDTO.getProjectId(),true);
			EProjectStatus pStat= sProjectFloorStatus.getById(projectFloorDTO.getStatusId(), true);
			
			project.setProjectFloorDescription(projectFloorDTO.getFloorDescription());
			project.setProjectFloorValue(projectFloorDTO.getFloorValue());
			project.setProjectFloorRef(projectFloorDTO.getFloorRef());
			project.setProjectFloorMeasurement(projectFloorDTO.getFloorMeasurement());
			project.setProjectFloorProject(proj);
			project.setProjectFloorStatus(pStat);
			
			return pFloorDAO.save(project);
		}
		
		@Override
		public void delete(ProjectFloorDTO projectFloorDTO) {
			
			EProjectFloor project = getById(projectFloorDTO.getId(), true);
			
			pFloorDAO.delete(project);
		}
	
		  @SuppressWarnings("unchecked")
		    public Specification<EProjectFloor> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EProjectFloor> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EProjectFloor>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }
	
}
