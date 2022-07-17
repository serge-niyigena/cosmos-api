package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EItemType;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemTypeDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private int id;
	
	private String name;
	private String desc;
	private String ItemSelectionType;
	
	public ItemTypeDTO(EItemType eItemType) {
		setName(eItemType.getName());
		setDesc(eItemType.getDescription());
		setItemSelectionType(eItemType.getISelectionType());
	}

}
