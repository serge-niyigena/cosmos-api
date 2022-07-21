package com.cosmos.dtos.project;

import java.util.ArrayList;
import java.util.List;

import com.cosmos.models.project.EProjectUser;
import com.cosmos.models.setups.EUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectUserDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer projectUserId;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private ProjectDTO projectUserProject;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private List<UserDTO> projectUsers;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer projectUserProjectId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Integer> projectUsersIds;
	
	public ProjectUserDTO(EProjectUser eProjectUser) {
		setProjectUserId(eProjectUser.getId());
		setProjectUserProject(new ProjectDTO(eProjectUser.getProjectUserProject()));
		setProjectUsers(setProjectUsersList(eProjectUser.getProjectUserUsers()));
	}
	
	private List<UserDTO> setProjectUsersList(List<EUser> projectUsers ) {
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		
		for(EUser projUser: projectUsers) {
			usersList.add(new UserDTO(projUser));
		}
		
		return usersList;
	}

}
