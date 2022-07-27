package com.cosmos.dtos.setups;

import java.io.Serializable;

import com.cosmos.models.setups.ERoleGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(content = Include.NON_NULL)
public class RoleGroupDTO  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer roleGroupId;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	@JsonIgnoreProperties(value={"roles","users"})
	private GroupDTO group;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	@JsonIgnoreProperties(value={"groups"})
	private RoleDTO role;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer groupId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer roleId;
	
	public RoleGroupDTO(ERoleGroup eRoleGroup) {
		setRoleGroupId(eRoleGroup.getId());
		setGroup(new GroupDTO(eRoleGroup.getGroup(),false,false));
		setRole(new RoleDTO(eRoleGroup.getRole(),false,false));
	}
	
//	@ApiModelProperty( hidden = true)
//	public List<RoleDTO> setRoles(List<ERole> roles){
//		List<RoleDTO> rolesList= new ArrayList<RoleDTO>();
//		
//		for(ERole role: roles) {
//			rolesList.add(new RoleDTO(role));
//		}
//		
//		return rolesList;
//		
//	}
	
//	@Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((roleGroupId == null) ? 0 : roleGroupId.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        RoleGroupDTO other = (RoleGroupDTO) obj;
//        if (roleGroupId == null) {
//            if (other.roleGroupId != null)
//                return false;
//        } else if (!roleGroupId.equals(other.roleGroupId))
//            return false;
//        return true;
//    }
//
//	public Integer getRoleGroupId() {
//		return roleGroupId;
//	}
//
//	public void setRoleGroupId(Integer roleGroupId) {
//		this.roleGroupId = roleGroupId;
//	}
//
//	public GroupDTO getGroup() {
//		return group;
//	}
//
//	public void setGroup(GroupDTO group) {
//		this.group = group;
//	}
//
//	public RoleDTO getRole() {
//		return role;
//	}
//
//	public void setRole(RoleDTO role) {
//		this.role = role;
//	}
//
//	public Integer getGroupId() {
//		return groupId;
//	}
//
//	public void setGroupId(Integer groupId) {
//		this.groupId = groupId;
//	}
//
//	public Integer getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Integer roleId) {
//		this.roleId = roleId;
//	}

}
