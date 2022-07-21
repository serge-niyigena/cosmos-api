package com.cosmos.dtos.setups;

import com.cosmos.models.setups.ERole;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer roleId;
	
	private String roleName;
	
	private String roleDesc;
	
	public RoleDTO(ERole eRole) {
		setRoleId(eRole.getId());
		setRoleName(eRole.getName());
		setRoleDesc(eRole.getDesc());
	}

}
