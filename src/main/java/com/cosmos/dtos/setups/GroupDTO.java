package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EGroup;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupDTO {

	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer id;
	
	private String name;
	
	private String desc;
	
	public GroupDTO(EGroup eGroup) {
		setId(eGroup.getId());
		setName(eGroup.getName());
		setDesc(eGroup.getDesc());
	}
	
}
