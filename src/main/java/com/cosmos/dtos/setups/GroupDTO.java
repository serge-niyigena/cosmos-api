package com.cosmos.dtos.setups;

import java.util.List;
import java.util.Set;

import com.cosmos.models.setups.EGroup;
import com.cosmos.models.setups.EGroupUsers;
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
public class GroupDTO {

	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer id;
	
	private String name;
	
	private String desc;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Integer> rolesIds;
	
	@JsonIgnoreProperties(ignoreUnknown = true, value = "group")
	private List<RoleGroupDTO> roles;
	
	//@JsonIgnoreProperties(ignoreUnknown = true, value = {"group"})
	private List<GroupUserDTO> users;
	
	public GroupDTO(EGroup eGroup) {
		setId(eGroup.getId());
		setName(eGroup.getName());
		setDesc(eGroup.getDesc());
		setRoles(eGroup.getRoles());
	}
	
	public void setRoles(List<ERoleGroup> roleGroup) {
		for(ERoleGroup rg: roleGroup) {
			this.roles.add(new RoleGroupDTO(rg));;
		}
	}
	
	public void setUsers(List<EGroupUsers> groupUsers) {
		for(EGroupUsers u: groupUsers) {
			this.users.add(new GroupUserDTO(u));;
		}
	}

//	 @Override
//	    public int hashCode() {
//	        final int prime = 31;
//	        int result = 1;
//	        result = prime * result + ((id == null) ? 0 : id.hashCode());
//	        return result;
//	    }
//
//	    @Override
//	    public boolean equals(Object obj) {
//	        if (this == obj)
//	            return true;
//	        if (obj == null)
//	            return false;
//	        if (getClass() != obj.getClass())
//	            return false;
//	        GroupDTO other = (GroupDTO) obj;
//	        if (id == null) {
//	            if (other.id != null)
//	                return false;
//	        } else if (!id.equals(other.id))
//	            return false;
//	        return true;
//	    }
}
