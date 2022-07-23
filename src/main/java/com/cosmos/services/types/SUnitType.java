package com.cosmos.services.types;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.setups.UnitTypeDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EUnitType;
import com.cosmos.repositories.UnitTypeDAO;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;


@Service
public class SUnitType implements IUnitType{
	
	   @Autowired
	   private UnitTypeDAO unitTypeDAO;

	   @Autowired
	   private GlobalFunctions globalFunction;
	   
	   @Autowired
	   private SpecFactory specFactory;

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

		@Override
		public EUnitType update( UnitTypeDTO unitTypeDTO) {
			EUnitType unitType= getById(unitTypeDTO.getId(), true);
			unitType.setDescription(unitTypeDTO.getDesc());
			unitType.setName(unitTypeDTO.getName());
			
			return unitTypeDAO.save(unitType);
		}

		@Override
		public void delete(Integer unitTypeId) {
			
			unitTypeDAO.deleteById(unitTypeId);
		}

		@Override
		public Page<EUnitType> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
			String searchQuery = pageDTO.getSearch();

	        PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);
	        return unitTypeDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
		}
		
		 @SuppressWarnings("unchecked")
		    public Specification<EUnitType> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EUnitType> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EUnitType>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    
	}	

		

}
