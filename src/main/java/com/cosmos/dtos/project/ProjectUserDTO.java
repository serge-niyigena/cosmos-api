package com.cosmos.dtos.project;

import com.cosmos.models.project.EProjectUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectUserDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer projectUserId;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private ProjectDTO projectUserProject;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private UserDTO projectUser;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer projectUserProjectId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer projectUserUserId;
	
	public ProjectUserDTO(EProjectUser eProjectUser) {
		setProjectUserId(eProjectUser.getId());
		setProjectUserProject(new ProjectDTO(eProjectUser.getProjectUserProject(),false,true));
		setProjectUser(new UserDTO(eProjectUser.getProjectUserUsers(),false,false));
	}

}
