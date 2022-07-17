package com.cosmos.services.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.FloorRoomDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.project.EProjectFloor;
import com.cosmos.models.project.EFloorRoom;
import com.cosmos.repositories.FloorRoomDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.globalFunctions;

@Service
public class SFloorRoom implements IFloorRoom {

	 @Autowired
	 private FloorRoomDAO floorRoomDAO;

	 @Autowired
	 private IProjectFloor sFloorRoomProjectFloor;
	 
	 
	 @Autowired
	 private SpecFactory specFactory;
	 
	 @Autowired
	 private globalFunctions globalFunction;
	 
	    @Override
	    public Page<EFloorRoom> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return floorRoomDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public Optional<EFloorRoom> getById(Integer floorId) {
	    	
	        Optional<EFloorRoom> floorRoom = floorRoomDAO.findById(floorId);
	        
	        return floorRoom;
	    }
	  
		@Override
		public EFloorRoom create(FloorRoomDTO floorRoomDTO) {
			
			EFloorRoom fRoom = new EFloorRoom();
		
			EProjectFloor pFloor= sFloorRoomProjectFloor.getById(floorRoomDTO.getProjectFloorId(), true);
			
			fRoom.setFloorRoomDesc(floorRoomDTO.getFloorRoomDesc());
			fRoom.setFloorRoomValue(floorRoomDTO.getFloorRoomValue());
			fRoom.setSize(floorRoomDTO.getSize());
			fRoom.setRoomProjectFloor(pFloor);
			
			return save(fRoom);
		}
		
		public EFloorRoom save(EFloorRoom eFloorRoom) {
			return floorRoomDAO.save(eFloorRoom);
		}

		  @SuppressWarnings("unchecked")
		    public Specification<EFloorRoom> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EFloorRoom> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EFloorRoom>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }


		@Override
		public EFloorRoom getById(Integer floorRoomId, Boolean throwsException) {
			
			 Optional<EFloorRoom> floorRoom = floorRoomDAO.findById(floorRoomId);
			 
			 if(!floorRoom.isPresent() && throwsException) {
				 throw new InvalidInputException("Floor Room with given id not found", "projectId");
		        }  
		     return floorRoom.get();
		}
	
}
