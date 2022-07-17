package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EUserTypes;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTypeDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private int id;
	
	private String name;
	private String desc;
	
	public UserTypeDTO(EUserTypes eUserType) {
		setId(eUserType.getId());
		setName(eUserType.getName());
		setDesc(eUserType.getDescription());
	}

}
