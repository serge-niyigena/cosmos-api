package com.cosmos.services.categories;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.ProjectCategoryDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EProjectCategory;
import com.cosmos.repositories.ProjectCategoryDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;


@Service
public class SProjectCategory implements IProjectCategory{
	
	   @Autowired
	   private ProjectCategoryDAO projectCategoryDAO;

	   @Autowired
	   private SpecFactory specFactory;
	   
	   @Autowired
	   private GlobalFunctions globalFunction;

	   
	   @Override
	    public Page<EProjectCategory> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
	          return projectCategoryDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }
	   

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
		public EProjectCategory create(ProjectCategoryDTO projCategoryDTO) {
			
			EProjectCategory projCategory = new EProjectCategory();
			
			projCategory.setDesc(projCategoryDTO.getDesc());
			projCategory.setName(projCategoryDTO.getName());
			
			return projectCategoryDAO.save(projCategory);
		}
		
		@Override
		public EProjectCategory update(Integer id,ProjectCategoryDTO projCategoryDTO) {
			
			EProjectCategory projCategory = getById(id, true);
			
			projCategory.setDesc(projCategoryDTO.getDesc());
			projCategory.setName(projCategoryDTO.getName());
			
			return projectCategoryDAO.save(projCategory);
		}
		
		@Override
		public void delete(ProjectCategoryDTO projCategoryDTO) {
			
			EProjectCategory projCategory = getById(projCategoryDTO.getId(), true);
			
			projectCategoryDAO.delete(projCategory);;
		}
		

		@SuppressWarnings("unchecked")
	    public Specification<EProjectCategory> buildFilterSpec(String searchQuery, List<String> allowedFields) {

	        SpecBuilder<EProjectCategory> specBuilder = new SpecBuilder<>();

	        specBuilder = (SpecBuilder<EProjectCategory>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

	        return specBuilder.build();
	    }

}
