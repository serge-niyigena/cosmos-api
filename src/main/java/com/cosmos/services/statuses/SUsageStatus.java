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
			
			EUsageStatus usageStatus = new EUsageStatus();
			
			usageStatus.setDescription(uStatusDTO.getDesc());
			usageStatus.setName(uStatusDTO.getName());
			
			return usageStatusDAO.save(usageStatus);
		}
		

		@Override
		public EUsageStatus update( UsageStatusDTO uStatusDTO) {
			
			EUsageStatus usageStatus = getById(uStatusDTO.getId(), true);
			
			usageStatus.setDescription(uStatusDTO.getDesc());
			usageStatus.setName(uStatusDTO.getName());
		
			return usageStatusDAO.save(usageStatus);
		}

		
		@Override
		public void delete(UsageStatusDTO usageStatusDTO) {
			
			usageStatusDAO.delete(getById(usageStatusDTO.getId(), true));
		}
		


}
