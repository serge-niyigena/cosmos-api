package com.cosmos.dtos.setups;

import java.util.ArrayList;
import java.util.List;

import com.cosmos.dtos.project.UserDTO;
import com.cosmos.models.setups.EGroupUsers;
import com.cosmos.models.setups.EUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupUserDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer groupUserId;
	
	private GroupDTO groupUserGroup;
	
	private List<UserDTO> groupUserUsers;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Integer> userIds;
	
	public GroupUserDTO(EGroupUsers eGroupUser) {
		setGroupUserId(eGroupUser.getId());
		setGroupUserGroup(new GroupDTO(eGroupUser.getEGroup()));
		setUsers(eGroupUser.getEUsers());
	}
	
	private List<UserDTO> setUsers(List<EUser> users){
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		
		for(EUser user: users) {
			usersList.add(new UserDTO(user));
		}
		return usersList;
	}

}
