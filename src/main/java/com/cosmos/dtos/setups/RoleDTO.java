package com.cosmos.dtos.setups;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.cosmos.models.setups.ERole;
import com.cosmos.models.setups.ERoleGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer roleId;
	
	private String roleName;
	
	private String roleDesc;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"role","users"})
	private List<GroupDTO> groups = new ArrayList<>();
	
	public RoleDTO(ERole eRole, boolean groups,boolean none) {
		setRoleId(eRole.getId());
		setRoleName(eRole.getName());
		setRoleDesc(eRole.getDesc());
		if(groups && eRole.getGroups()!=null){
		addGroups(eRole.getGroups());
		}
	}
	
	public void addGroups(List<ERoleGroup> roleGroups) {
		for(ERoleGroup rg: roleGroups) {
        this.groups.add(new GroupDTO(rg.getGroup(),false,false));
		}
    }

}
