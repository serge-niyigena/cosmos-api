package com.cosmos.services.statuses;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.ProjectStatusDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EProjectStatus;
import com.cosmos.repositories.ProjectStatusDAO;


@Service
public class SProjectStatus implements IProjectStatus{
	
	   @Autowired
	   private ProjectStatusDAO projectStatusDAO;


	    @Override
	    public List<EProjectStatus> getAll() {
	        return projectStatusDAO.findAll();
	    }

	    @Override
	    public Optional<EProjectStatus> getById(Integer projectStatusId) {
	        return projectStatusDAO.findById(projectStatusId);
	    }

	    @Override
	    public EProjectStatus getById(Integer projectStatusId, Boolean throwException) {
	        Optional<EProjectStatus> projectStatus = projectStatusDAO.findById(projectStatusId);
	        if (!projectStatus.isPresent() && throwException) {
	            throw new InvalidInputException("project status with given id not found", "projectStatusId");
	        }
	        return projectStatus.get();
	    }
	  
		@Override
		public EProjectStatus create(ProjectStatusDTO uStatusDTO) {
			
			EProjectStatus projStatus = new EProjectStatus();
			
			projStatus.setDescription(uStatusDTO.getDesc());
			projStatus.setName(uStatusDTO.getName());
		
			return projectStatusDAO.save(projStatus);
		}
		
		@Override
		public EProjectStatus update(ProjectStatusDTO uStatusDTO) {
			
			EProjectStatus projStatus = getById(uStatusDTO.getId(), true);
			
			projStatus.setDescription(uStatusDTO.getDesc());
			projStatus.setName(uStatusDTO.getName());
		
			return projectStatusDAO.save(projStatus);
		}

		
		@Override
		public void delete(ProjectStatusDTO projStatusDTO) {
			
			projectStatusDAO.delete(getById(projStatusDTO.getId(),true));
		}
		

}
