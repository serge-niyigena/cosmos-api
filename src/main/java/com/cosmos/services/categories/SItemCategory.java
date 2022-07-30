package com.cosmos.services.categories;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.ItemCategoryDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EItemCategory;
import com.cosmos.repositories.ItemCategoryDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;


@Service
public class SItemCategory implements IItemCategory{
	
	   @Autowired
	   private ItemCategoryDAO itemCategoryDAO;

	   @Autowired
	   private SpecFactory specFactory;
	   
	   @Autowired
	   private GlobalFunctions globalFunction;

	   
	   @Override
	    public Page<EItemCategory> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
	          return itemCategoryDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }
	   
	    @Override
	    public List<EItemCategory> getAll() {
	        return itemCategoryDAO.findAll();
	    }

	    @Override
	    public Optional<EItemCategory> getById(Integer itemCategoryId) {
	        return itemCategoryDAO.findById(itemCategoryId);
	    }

	    @Override
	    public EItemCategory getById(Integer itemCategoryId, Boolean throwException) {
	        Optional<EItemCategory> itemCategory = itemCategoryDAO.findById(itemCategoryId);
	        if (!itemCategory.isPresent() && throwException) {
	            throw new InvalidInputException("item category with given id not found", "itemCategoryId");
	        }
	        return itemCategory.get();
	    }
	  
		@Override
		public EItemCategory create(ItemCategoryDTO itemCategoryDTO) {
			
			EItemCategory iCategory = new EItemCategory();
			
			iCategory.setDescription(itemCategoryDTO.getDesc());
			iCategory.setName(itemCategoryDTO.getName());
			
			return itemCategoryDAO.save(iCategory) ;
		}
		
		@Override
		public EItemCategory update(Integer catId,ItemCategoryDTO itemCategoryDTO) {
			
			EItemCategory iCategory =getById(catId,true);
			
			iCategory.setDescription(itemCategoryDTO.getDesc());
			iCategory.setName(itemCategoryDTO.getName());
			
			return itemCategoryDAO.save(iCategory);
		}
		
		@Override
		public void delete(ItemCategoryDTO itemCategoryDTO) {
			
			EItemCategory iCategory =getById(itemCategoryDTO.getId(), true);
			
			itemCategoryDAO.delete(iCategory);
		}
		
		@SuppressWarnings("unchecked")
	    public Specification<EItemCategory> buildFilterSpec(String searchQuery, List<String> allowedFields) {

	        SpecBuilder<EItemCategory> specBuilder = new SpecBuilder<>();

	        specBuilder = (SpecBuilder<EItemCategory>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

	        return specBuilder.build();
	    }
}
