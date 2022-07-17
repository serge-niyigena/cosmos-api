package com.cosmos.services.project;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.RoomItemDTO;
import com.cosmos.models.project.ERoomItem;
import com.cosmos.models.setups.EUsageStatus;
import com.cosmos.models.project.EFloorRoom;
import com.cosmos.models.project.EItem;
import com.cosmos.repositories.RoomItemDAO;
import com.cosmos.services.items.IItem;
import com.cosmos.services.statuses.IUsageStatus;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.globalFunctions;

@Service
public class SRoomItem implements IRoomItem {
	
	@Autowired
	 private RoomItemDAO roomItemDAO;

	 @Autowired
	 private IItem sItem;
	 
	 @Autowired
	 private IFloorRoom sFloorRoom;
	 
	 @Autowired
	 private IUsageStatus sUsageStatus;
	 
	 
	 @Autowired
	 private SpecFactory specFactory;
	 
	 @Autowired
	 private globalFunctions globalFunction;
	 
	    @Override
	    public Page<ERoomItem> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return roomItemDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public ERoomItem getById(Integer floorId,Boolean throwException) {
	    	
	        ERoomItem floorRoom = roomItemDAO.getById(floorId);
	        
	        return floorRoom;
	    }
	  
		@Override
		public ERoomItem create(RoomItemDTO roomItemDTO) {
			
			ERoomItem roomItem = new ERoomItem();
		
			EFloorRoom fRoom= sFloorRoom.getById(roomItemDTO.getFloorRoomId(), true);
			EItem item=  sItem.getById(roomItemDTO.getItemId(),true);
			EUsageStatus usageStatus= sUsageStatus.getById(roomItemDTO.getRoomItemStatusId(), true);
			
			roomItem.setItemMaximumQuantity(roomItemDTO.getItemMaximumQuantity());
			roomItem.setItemNormalQuantity(roomItemDTO.getItemNormalQuantity());
			roomItem.setItemUsedQuantity(roomItemDTO.getItemUsedQuantity());
			roomItem.setRoomStatusReport(roomItemDTO.getStatusReport());
			roomItem.setRoomItemFloorRoom(fRoom);
			roomItem.setRoomItemItem(item);
			roomItem.setRoomItemStatus(usageStatus);
			
			return save(roomItem);
		}
		
		public ERoomItem save(ERoomItem eRoomItem) {
			return roomItemDAO.save(eRoomItem);
		}

		  @SuppressWarnings("unchecked")
		    public Specification<ERoomItem> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<ERoomItem> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<ERoomItem>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }

}
