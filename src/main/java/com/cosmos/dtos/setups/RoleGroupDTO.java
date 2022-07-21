package com.cosmos.dtos.setups;

import java.util.ArrayList;
import java.util.List;

import com.cosmos.models.setups.ERoleGroup;
import com.cosmos.models.setups.ERole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleGroupDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer roleGroupId;
	
	private GroupDTO roleGroupGroup;
	
	private List<RoleDTO> roleGroupRoles;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer roleGroupGroupId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Integer> roleGroupRolesId;
	
	public RoleGroupDTO(ERoleGroup eRoleGroup) {
		setRoleGroupId(eRoleGroup.getId());
		setRoleGroupGroup(new GroupDTO(eRoleGroup.getEGroup()));
		setRoleGroupRoles(setRoles(eRoleGroup.getERoles()));
	}
	
	private List<RoleDTO> setRoles(List<ERole> roles){
		List<RoleDTO> rolesList= new ArrayList<RoleDTO>();
		
		for(ERole role: roles) {
			rolesList.add(new RoleDTO(role));
		}
		
		return rolesList;
		
	}

}
