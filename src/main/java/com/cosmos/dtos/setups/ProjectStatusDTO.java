package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EProjectStatus;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectStatusDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private int id;
	
	private String name;
	private String desc;
	
	public ProjectStatusDTO(EProjectStatus eProjectStatus) {
		setId(eProjectStatus.getId());
		setName(eProjectStatus.getName());
		setDesc(eProjectStatus.getDescription());
	}

}
