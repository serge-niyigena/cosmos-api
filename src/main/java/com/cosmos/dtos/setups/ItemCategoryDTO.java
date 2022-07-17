package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EItemCategory;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemCategoryDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private int id;
	
	private String name;
	private String desc;
	
	public ItemCategoryDTO(EItemCategory eItemCategory) {
		setName(eItemCategory.getName());
		setDesc(eItemCategory.getDescription());
	}

}
