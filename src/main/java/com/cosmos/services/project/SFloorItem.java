package com.cosmos.services.project;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.cosmos.utils.GlobalFunctions;

@Service
public class SFloorItem implements IFloorItem {
	
	@Value("${default.value.status.used}")
	private Integer usedStatusId;
	
	@Value("${default.value.status.under_used}")
	private Integer underStatusId;
	
	@Value("${default.value.status.over_used}")
	private Integer overUsedStatusId;
	
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
	 private GlobalFunctions globalFunction;
	 
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
		
			EProjectFloor pFloor= sProjectFloor.getById(floorItemDTO.getFloorItemProjectFloorId(), true);
			EItem item=  sItem.getById(floorItemDTO.getFloorItemItemId(),true);
			EUsageStatus usageStatus= sUsageStatus.getById(usedStatusId, true);
			
			floorItem.setFloorItemMaximumQuantity(floorItemDTO.getFloorItemMaximumQuantity());
			floorItem.setFloorItemNormalQuantity(floorItemDTO.getFloorItemNormalQuantity());
			floorItem.setFloorItemUsedQuantity(floorItemDTO.getFloorItemUsedQuantity());
			floorItem.setFloorItemStatusReport(floorItemDTO.getFloorItemStatusReport());
			floorItem.setProjectFloor(pFloor);
			floorItem.setFloorItemItem(item);
			floorItem.setFloorItemStatus(usageStatus);
			
			return floorItemDAO.save(floorItem);
		}
		
		@Override
		public EFloorItem update(Integer id, FloorItemDTO floorItemDTO) {
			
			EFloorItem floorItem = getById(id, true);
		
			EProjectFloor fProject= sProjectFloor.getById(floorItemDTO.getFloorItemProjectFloorId(), true);
			EItem item=  sItem.getById(floorItemDTO.getFloorItemItemId(),true);
			EUsageStatus usageStatus= sUsageStatus.getById(usedStatusId, true);
			
			floorItem.setFloorItemMaximumQuantity(floorItemDTO.getFloorItemMaximumQuantity());
			floorItem.setFloorItemNormalQuantity(floorItemDTO.getFloorItemNormalQuantity());
			floorItem.setFloorItemUsedQuantity(floorItemDTO.getFloorItemUsedQuantity());
			floorItem.setFloorItemStatusReport(floorItemDTO.getFloorItemStatusReport());
			floorItem.setProjectFloor(fProject);
			floorItem.setFloorItemItem(item);
			floorItem.setFloorItemStatus(usageStatus);
			
			return floorItemDAO.save(floorItem);
		}
		
		@Override
		public void delete(FloorItemDTO floorItemDTO) {
			
			EFloorItem floorItem = getById(floorItemDTO.getId(), true);
			
			floorItemDAO.delete(floorItem);
		}
		
		
		  @SuppressWarnings("unchecked")
		    public Specification<EFloorItem> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EFloorItem> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EFloorItem>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }


		@Override
		public EFloorItem updateUsedItem(Integer id, FloorItemDTO used) {
			
			EFloorItem floorItem = getById(id, true);
			floorItem.setFloorItemUsedQuantity(floorItem.getFloorItemUsedQuantity()+used.getFloorItemUsedQuantity());
			return floorItemDAO.save(floorItem);
		}

}
