package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EUserType;
import com.cosmos.repositories.UserTypeDAO;


@Service
public class SUserType implements IUserType{
	
	   @Autowired
	   private UserTypeDAO userTypeDAO;


	    @Override
	    public List<EUserType> getAll() {
	        return userTypeDAO.findAll();
	    }

	    @Override
	    public Optional<EUserType> getById(Integer userTypeId) {
	        return userTypeDAO.findById(userTypeId);
	    }

	    @Override
	    public EUserType getById(Integer userTypeId, Boolean throwException) {
	        Optional<EUserType> userType = userTypeDAO.findById(userTypeId);
	        if (!userType.isPresent() && throwException) {
	            throw new InvalidInputException("property type with given id not found", "userTypeId");
	        }
	        return userType.get();
	    }
	  
		@Override
		public EUserType create(UserTypeDTO uTypeDTO) {
			
			EUserType propType = new EUserType();
			
			propType.setDescription(uTypeDTO.getDesc());
			propType.setName(uTypeDTO.getName());
			save(propType);
			return propType;
		}
		
		public void save(EUserType eUserType) {
			userTypeDAO.save(eUserType);
		}

		

}
