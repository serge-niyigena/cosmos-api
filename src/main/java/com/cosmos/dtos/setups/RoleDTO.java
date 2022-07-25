package com.cosmos.dtos.setups;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.cosmos.models.setups.ERole;
import com.cosmos.models.setups.ERoleGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer roleId;
	
	private String roleName;
	
	private String roleDesc;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Integer> groupsIds;
	
	@JsonIgnoreProperties("role")
	private List<RoleGroupDTO> groups;
	
	public RoleDTO(ERole eRole) {
		setRoleId(eRole.getId());
		setRoleName(eRole.getName());
		setRoleDesc(eRole.getDesc());
		addGroups(eRole.getGroups());
	}
	
	public void addGroups(List<ERoleGroup> roleGroups) {
		for(ERoleGroup rg: roleGroups) {
        this.groups.add(new RoleGroupDTO(rg));
		}
    }

}
