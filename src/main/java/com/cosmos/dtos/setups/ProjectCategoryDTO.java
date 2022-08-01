package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EProjectCategory;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectCategoryDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private int id;
	
	private String name;
	private String desc;
	
	public ProjectCategoryDTO(EProjectCategory eProjectCategory) {
		setId(eProjectCategory.getId());
		setName(eProjectCategory.getName());
		setDesc(eProjectCategory.getDesc());
	}

}
