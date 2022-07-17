package com.cosmos.services.statuses;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.dtos.setups.UsageStatusDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EUsageStatus;
import com.cosmos.repositories.UsageStatusDAO;


@Service
public class SUsageStatus implements IUsageStatus{
	
	   @Autowired
	   private UsageStatusDAO usageStatusDAO;


	    @Override
	    public List<EUsageStatus> getAll() {
	        return usageStatusDAO.findAll();
	    }

	    @Override
	    public Optional<EUsageStatus> getById(Integer usageStatusId) {
	        return usageStatusDAO.findById(usageStatusId);
	    }

	    @Override
	    public EUsageStatus getById(Integer usageStatusId, Boolean throwException) {
	        Optional<EUsageStatus> usageStatus = usageStatusDAO.findById(usageStatusId);
	        if (!usageStatus.isPresent() && throwException) {
	            throw new InvalidInputException("property type with given id not found", "usageStatusId");
	        }
	        return usageStatus.get();
	    }
	  
		@Override
		public EUsageStatus create(UsageStatusDTO uStatusDTO) {
			
			EUsageStatus propType = new EUsageStatus();
			
			propType.setDescription(uStatusDTO.getDesc());
			propType.setName(uStatusDTO.getName());
			save(propType);
			return propType;
		}
		
		public void save(EUsageStatus ePropType) {
			usageStatusDAO.save(ePropType);
		}

		

}
