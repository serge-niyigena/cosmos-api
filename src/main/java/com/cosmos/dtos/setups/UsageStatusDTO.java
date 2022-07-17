package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EUsageStatus;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsageStatusDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private int id;
	
	private String name;
	private String desc;
	
	public UsageStatusDTO(EUsageStatus eUsageStatus) {
		setId(eUsageStatus.getId());
		setName(eUsageStatus.getName());
		setDesc(eUsageStatus.getDescription());
	}

}
