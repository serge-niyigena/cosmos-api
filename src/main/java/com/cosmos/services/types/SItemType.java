package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.ItemTypeDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EItemType;
import com.cosmos.repositories.ItemTypeDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SItemType implements IItemType {

	   @Autowired
	   private ItemTypeDAO itemTypeDAO;

	   @Autowired
	   private GlobalFunctions globalFunction;

	   @Autowired
	   private SpecFactory specFactory;
	  
	    @Override
	    public Optional<EItemType> getById(Integer itemTypeId) {
	        return itemTypeDAO.findById(itemTypeId);
	    }

	    @Override
	    public EItemType getById(Integer itemTypeId, Boolean throwException) {
	        Optional<EItemType> itemType = itemTypeDAO.findById(itemTypeId);
	        if (!itemType.isPresent() && throwException) {
	            throw new InvalidInputException("property type with given id not found", "itemTypeId");
	        }
	        return itemType.get();
	    }
	  
		@Override
		public EItemType create(ItemTypeDTO itemTypeDTO) {
			
			EItemType itemType = new EItemType();
			
			itemType.setDescription(itemTypeDTO.getDesc());
			itemType.setName(itemTypeDTO.getName());
			
			return itemTypeDAO.save(itemType);
		}
		
		@Override
		public EItemType update(ItemTypeDTO itemTypeDTO) {
			
			
			EItemType itemType = getById(itemTypeDTO.getId(), true);
			
			itemType.setDescription(itemTypeDTO.getDesc());
			itemType.setName(itemTypeDTO.getName());
			
			return itemTypeDAO.save(itemType);
		}
		
		@Override
		public void delete(ItemTypeDTO itemTypeDTO) {
			
		 itemTypeDAO.delete(getById(itemTypeDTO.getId(), true));
		}
		

		@Override
		public Page<EItemType> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
			String searchQuery = pageDTO.getSearch();

	        PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
	        return itemTypeDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
		}
		
		 @SuppressWarnings("unchecked")
		    public Specification<EItemType> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EItemType> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EItemType>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    
	}	
	
}
