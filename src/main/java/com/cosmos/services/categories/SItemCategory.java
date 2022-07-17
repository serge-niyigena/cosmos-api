package com.cosmos.services.categories;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.ItemCategoryDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EItemCategory;
import com.cosmos.repositories.ItemCategoryDAO;


@Service
public class SItemCategory implements IItemCategory{
	
	   @Autowired
	   private ItemCategoryDAO itemCategoryDAO;


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
		public EItemCategory create(ItemCategoryDTO uStatusDTO) {
			
			EItemCategory propType = new EItemCategory();
			
			propType.setDescription(uStatusDTO.getDesc());
			propType.setName(uStatusDTO.getName());
			save(propType);
			return propType;
		}
		
		public void save(EItemCategory ePropType) {
			itemCategoryDAO.save(ePropType);
		}

		

}
