package com.cosmos.services.categories;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.ProjectCategoryDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EProjectCategory;
import com.cosmos.repositories.ProjectCategoryDAO;


@Service
public class SProjectCategory implements IProjectCategory{
	
	   @Autowired
	   private ProjectCategoryDAO projectCategoryDAO;


	    @Override
	    public List<EProjectCategory> getAll() {
	        return projectCategoryDAO.findAll();
	    }

	    @Override
	    public Optional<EProjectCategory> getById(Integer projectCategoryId) {
	        return projectCategoryDAO.findById(projectCategoryId);
	    }

	    @Override
	    public EProjectCategory getById(Integer projectCategoryId, Boolean throwException) {
	        Optional<EProjectCategory> projectCategory = projectCategoryDAO.findById(projectCategoryId);
	        if (!projectCategory.isPresent() && throwException) {
	            throw new InvalidInputException("project category with given id not found", "projectCategoryId");
	        }
	        return projectCategory.get();
	    }
	  
		@Override
		public EProjectCategory create(ProjectCategoryDTO uStatusDTO) {
			
			EProjectCategory projCategory = new EProjectCategory();
			
			projCategory.setDescription(uStatusDTO.getDesc());
			projCategory.setName(uStatusDTO.getName());
			save(projCategory);
			return projCategory;
		}
		
		public void save(EProjectCategory ePropType) {
			projectCategoryDAO.save(ePropType);
		}

		

}
