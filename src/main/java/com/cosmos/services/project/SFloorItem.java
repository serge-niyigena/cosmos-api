package com.cosmos.services.project;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.FloorItemDTO;
import com.cosmos.models.project.EFloorItem;
import com.cosmos.models.setups.EUsageStatus;
import com.cosmos.models.project.EProjectFloor;
import com.cosmos.models.project.EItem;
import com.cosmos.repositories.FloorItemDAO;
import com.cosmos.services.items.IItem;
import com.cosmos.services.statuses.IUsageStatus;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.globalFunctions;

@Service
public class SFloorItem implements IFloorItem {
	
	@Autowired
	 private FloorItemDAO floorItemDAO;

	 @Autowired
	 private IItem sItem;
	 
	 @Autowired
	 private IProjectFloor sProjectFloor;
	 
	 @Autowired
	 private IUsageStatus sUsageStatus;
	 
	 
	 @Autowired
	 private SpecFactory specFactory;
	 
	 @Autowired
	 private globalFunctions globalFunction;
	 
	    @Override
	    public Page<EFloorItem> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return floorItemDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public EFloorItem getById(Integer floorId,Boolean throwException) {
	    	
	        EFloorItem projectFloor = floorItemDAO.getById(floorId);
	        
	        return projectFloor;
	    }
	  
		@Override
		public EFloorItem create(FloorItemDTO floorItemDTO) {
			
			EFloorItem floorItem = new EFloorItem();
		
			EProjectFloor fRoom= sProjectFloor.getById(floorItemDTO.getFloorItemProjectFloorId(), true);
			EItem item=  sItem.getById(floorItemDTO.getFloorItemItemId(),true);
			EUsageStatus usageStatus= sUsageStatus.getById(floorItemDTO.getFloorItemStatusId(), true);
			
			floorItem.setFloorItemMaximumQuantity(floorItemDTO.getFloorItemMaximumQuantity());
			floorItem.setFloorItemNormalQuantity(floorItemDTO.getFloorItemNormalQuantity());
			floorItem.setFloorItemUsedQuantity(floorItemDTO.getFloorItemUsedQuantity());
			floorItem.setFloorItemStatusReport(floorItemDTO.getFloorItemStatusReport());
			floorItem.setFloorItemProjectFloor(fRoom);
			floorItem.setFloorItemItem(item);
			floorItem.setFloorItemStatus(usageStatus);
			
			return save(floorItem);
		}
		
		public EFloorItem save(EFloorItem eFloorItem) {
			return floorItemDAO.save(eFloorItem);
		}

		  @SuppressWarnings("unchecked")
		    public Specification<EFloorItem> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EFloorItem> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EFloorItem>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }

}
