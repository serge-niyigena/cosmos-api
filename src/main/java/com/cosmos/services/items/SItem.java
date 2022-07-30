package com.cosmos.services.items;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.ItemDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.project.EItem;
import com.cosmos.models.setups.EItemCategory;
import com.cosmos.models.setups.EItemType;
import com.cosmos.models.setups.EUnitType;
import com.cosmos.repositories.ItemDAO;
import com.cosmos.services.categories.IItemCategory;
import com.cosmos.services.types.IItemType;
import com.cosmos.services.types.IUnitType;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SItem implements IItem {

	 @Autowired
	 private ItemDAO itemDAO;

	 @Autowired
	 private IItemCategory sItemCategory;
	 
	 @Autowired
	 private IItemType sItemType;
	 
	 @Autowired
	 private IUnitType sUnitType;
	 
	 @Autowired
	 private GlobalFunctions globalFunction;
	 
	 @Autowired
	 private SpecFactory specFactory;
	 
	    @Override
	    public Page<EItem> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
	          return itemDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	   
	    @Override
	    public Optional<EItem> getById(Integer itemId) {
	    	
	        Optional<EItem> item = itemDAO.findById(itemId);
	        
	        return item;
	    }
	  
		@Override
		public EItem create(ItemDTO itemDTO) {
			
			EItem item = new EItem();
			
			EItemCategory iCat= sItemCategory.getById(itemDTO.getItemCategoryId(),true);
			EUnitType uType= sUnitType.getById(itemDTO.getItemUnitTypeId(), true);
			EItemType iType= sItemType.getById(itemDTO.getItemTypeId(), true);
			
			item.setDesc(itemDTO.getDesc());
			item.setName(itemDTO.getName());
			item.setItemCategory(iCat);
			item.setItemType(iType);
			item.setItemUnitType(uType);
			
			return itemDAO.save(item);
		}
		
		@Override
		public EItem update(Integer id,ItemDTO itemDTO) {
			
			EItem item = getById(id, true);
			
			EItemCategory iCat= sItemCategory.getById(itemDTO.getItemCategoryId(),true);
			EUnitType uType= sUnitType.getById(itemDTO.getItemUnitTypeId(), true);
			EItemType iType= sItemType.getById(itemDTO.getItemTypeId(), true);
			
			item.setDesc(itemDTO.getDesc());
			item.setName(itemDTO.getName());
			item.setItemCategory(iCat);
			item.setItemType(iType);
			item.setItemUnitType(uType);
			
			return itemDAO.save(item);
		}
		
	
		  @SuppressWarnings("unchecked")
		    public Specification<EItem> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EItem> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EItem>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    
}


		@Override
		public EItem getById(Integer itemId, Boolean throwsException) {
				
			Optional<EItem> item = itemDAO.findById(itemId);
			 
			 if(!item.isPresent() && throwsException) {
				 throw new InvalidInputException("Item with given id not found", "projectId");
		        }  
		     return item.get();
		}
		
		@Override
		public void delete(ItemDTO item) {
			EItem eItem = getById(item.getId(),true);
			itemDAO.delete(eItem);
		}
		
}
