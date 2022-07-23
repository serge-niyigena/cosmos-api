package com.cosmos.services.statuses;

import java.util.List;
import java.util.Optional;

import com.cosmos.dtos.setups.UsageStatusDTO;
import com.cosmos.models.setups.EUsageStatus;


public interface IUsageStatus {

	EUsageStatus create(UsageStatusDTO usageStatusDTO);
	
	EUsageStatus update(UsageStatusDTO usageStatusDTO);
	
	void delete(UsageStatusDTO usageStatusDTO);
	
    List<EUsageStatus> getAll();

    Optional<EUsageStatus> getById(Integer usageStatusId);

    EUsageStatus getById(Integer usageStatusId, Boolean throwException);
	
}
