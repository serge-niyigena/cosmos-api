package com.cosmos.services.project;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.DamagedItemDTO;
import com.cosmos.models.project.EDamagedItem;
import com.cosmos.models.project.EFloorItem;
import com.cosmos.repositories.DamagedItemDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SDamagedItem implements IDamagedItem{

	 @Autowired
	 private SpecFactory specFactory;
	 
	 @Autowired
	 private GlobalFunctions globalFunction;
	 
	 @Autowired
	 private DamagedItemDAO damagedItemDAO;
	 
	 @Autowired
	 private IFloorItem sFloorItem;
	 
	    @Override
	    public Page<EDamagedItem> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return damagedItemDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public EDamagedItem getById(Integer floorId,Boolean throwException) {
	    	
	        EDamagedItem projectFloor = damagedItemDAO.getById(floorId);
	        
	        return projectFloor;
	    }
	  
		@Override
		public EDamagedItem create(DamagedItemDTO damagedItemDTO) {
			
			EDamagedItem damagedItem = new EDamagedItem();
		
			EFloorItem floorItem = sFloorItem.getById(damagedItemDTO.getDamagedFloorItemId(), true);
			
			damagedItem.setDamagedDesc(damagedItemDTO.getDamagedDesc());
			damagedItem.setDamagedDate(damagedItemDTO.getDamagedDate());
			damagedItem.setDamagedQuantity(damagedItem.getDamagedQuantity());
			damagedItem.setProjectFloorItemId(floorItem);
			
			return damagedItemDAO.save(damagedItem);
		}
		
		@Override
		public EDamagedItem update(DamagedItemDTO damagedItemDTO) {
			
			EDamagedItem damagedItem = getById(damagedItemDTO.getDamagedId(), true);
		
			EFloorItem floorItem = sFloorItem.getById(damagedItemDTO.getDamagedFloorItemId(), true);
			
			damagedItem.setDamagedDesc(damagedItemDTO.getDamagedDesc());
			damagedItem.setDamagedDate(damagedItemDTO.getDamagedDate());
			damagedItem.setDamagedQuantity(damagedItem.getDamagedQuantity());
			damagedItem.setProjectFloorItemId(floorItem);
			
			return damagedItemDAO.save(damagedItem);
		}
		
		@Override
		public void delete(DamagedItemDTO damagedItemDTO) {
			
			EDamagedItem damagedItem = getById(damagedItemDTO.getDamagedId(), true);
		
			 damagedItemDAO.delete(damagedItem);
		}
		

		  @SuppressWarnings("unchecked")
		    public Specification<EDamagedItem> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EDamagedItem> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EDamagedItem>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }

	
}

