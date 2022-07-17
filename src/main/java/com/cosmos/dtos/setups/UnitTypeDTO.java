package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EUnitType;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnitTypeDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private int id;
	
	private String name;
	private String desc;
	
	public UnitTypeDTO(EUnitType eUnitType) {
		setId(eUnitType.getId());
		setName(eUnitType.getName());
		setDesc(eUnitType.getDescription());
	}

}
