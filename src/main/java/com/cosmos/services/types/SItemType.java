package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.ItemTypeDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EItemType;
import com.cosmos.repositories.ItemTypeDAO;

@Service
public class SItemType implements IItemType {

	   @Autowired
	   private ItemTypeDAO itemTypeDAO;


	    @Override
	    public List<EItemType> getAll() {
	        return itemTypeDAO.findAll();
	    }

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
			
			EItemType propType = new EItemType();
			
			propType.setDescription(itemTypeDTO.getDesc());
			propType.setName(itemTypeDTO.getName());
			save(propType);
			return propType;
		}
		
		public void save(EItemType eUniType) {
			itemTypeDAO.save(eUniType);
		}

		
	
}
