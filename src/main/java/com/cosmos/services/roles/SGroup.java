package com.cosmos.services.roles;

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
import com.cosmos.repositories.GroupDAO;
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
		return save(group);
	}
	
	public EGroup save(EGroup eGroup) {
		return groupDAO.save(eGroup);
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
	

}
