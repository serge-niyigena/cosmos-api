package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EUserTypes;
import com.cosmos.repositories.UserTypeDAO;


@Service
public class SUserType implements IUserType{
	
	   @Autowired
	   private UserTypeDAO userTypeDAO;


	    @Override
	    public List<EUserTypes> getAll() {
	        return userTypeDAO.findAll();
	    }

	    @Override
	    public Optional<EUserTypes> getById(Integer userTypeId) {
	        return userTypeDAO.findById(userTypeId);
	    }

	    @Override
	    public EUserTypes getById(Integer userTypeId, Boolean throwException) {
	        Optional<EUserTypes> userType = userTypeDAO.findById(userTypeId);
	        if (!userType.isPresent() && throwException) {
	            throw new InvalidInputException("property type with given id not found", "userTypeId");
	        }
	        return userType.get();
	    }
	  
		@Override
		public EUserTypes create(UserTypeDTO uTypeDTO) {
			
			EUserTypes propType = new EUserTypes();
			
			propType.setDescription(uTypeDTO.getDesc());
			propType.setName(uTypeDTO.getName());
			save(propType);
			return propType;
		}
		
		public void save(EUserTypes eUserType) {
			userTypeDAO.save(eUserType);
		}

		

}
