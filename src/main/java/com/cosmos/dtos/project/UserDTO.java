package com.cosmos.dtos.project;

import java.util.List;

import com.cosmos.dtos.setups.GroupUserDTO;
import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.models.project.EProjectUser;
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
public class UserDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer id;
	
	private String userFullName;
	
	private String userPassword;
	
	private String userMobile;
	
	private String userEmail;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private UserTypeDTO userType;
	
	private List<GroupUserDTO> groups;
	
	private List<ProjectUserDTO> projects;
	
	private String userStatus;
	
	private String userReset;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer userTypeId;


	public UserDTO(EUser eUser) {
		setId(eUser.getId());
		setUserFullName(eUser.getUserFullName());
		setUserPassword(eUser.getUserPassword());
		setUserMobile(eUser.getUserMobile());
		setUserEmail(eUser.getUserEmail());
		setUserType(new UserTypeDTO(eUser.getEUserType()));
		setUserStatus(eUser.getUserActive());
		setUserReset(eUser.getUserReset());
		addGroups(eUser.getGroups());
		addProjects(eUser.getProjects());
	}
	
	
	public void addGroups(List<EGroupUsers> userGroups) {
		for(EGroupUsers ug: userGroups) {
        this.groups.add(new GroupUserDTO(ug));
		}
    }
	
	public void addProjects(List<EProjectUser> userProjects) {
		for(EProjectUser pu: userProjects) {
        this.projects.add(new ProjectUserDTO(pu));
		}
    }
	
}
