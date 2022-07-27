package com.cosmos.dtos.setups;


import com.cosmos.dtos.project.UserDTO;
import com.cosmos.models.setups.EGroupUsers;
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
public class GroupUserDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer groupUserId;
	
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"roles","users"})
	private GroupDTO groupUserGroup;
	
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"groups","projects"})
	private UserDTO groupUserUser;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer groupId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer userId;
	
	public GroupUserDTO(EGroupUsers eGroupUser) {
		setGroupUserId(eGroupUser.getId());
		setGroupUserGroup(new GroupDTO(eGroupUser.getEGroup(),false,false));
		setGroupUserUser(new UserDTO(eGroupUser.getEUsers(),false,false));
	}

}
