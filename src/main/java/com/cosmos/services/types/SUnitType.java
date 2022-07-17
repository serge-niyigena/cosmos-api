package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.UnitTypeDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EUnitType;
import com.cosmos.repositories.UnitTypeDAO;


@Service
public class SUnitType implements IUnitType{
	
	   @Autowired
	   private UnitTypeDAO unitTypeDAO;


	    @Override
	    public List<EUnitType> getAll() {
	        return unitTypeDAO.findAll();
	    }

	    @Override
	    public Optional<EUnitType> getById(Integer unitTypeId) {
	        return unitTypeDAO.findById(unitTypeId);
	    }

	    @Override
	    public EUnitType getById(Integer unitTypeId, Boolean throwException) {
	        Optional<EUnitType> unitType = unitTypeDAO.findById(unitTypeId);
	        if (!unitType.isPresent() && throwException) {
	            throw new InvalidInputException("property type with given id not found", "unitTypeId");
	        }
	        return unitType.get();
	    }
	  
		@Override
		public EUnitType create(UnitTypeDTO unitTypeDTO) {
			
			EUnitType propType = new EUnitType();
			
			propType.setDescription(unitTypeDTO.getDesc());
			propType.setName(unitTypeDTO.getName());
			save(propType);
			return propType;
		}
		
		public void save(EUnitType eUniType) {
			unitTypeDAO.save(eUniType);
		}

		

}
