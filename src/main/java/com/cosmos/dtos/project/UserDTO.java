package com.cosmos.dtos.project;

import java.util.ArrayList;
import java.util.List;
import com.cosmos.dtos.setups.GroupDTO;
import com.cosmos.dtos.setups.OrganizationDTO;
import com.cosmos.dtos.setups.RoleDTO;
import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.models.project.EProjectUser;
import com.cosmos.models.setups.EGroupUsers;
import com.cosmos.models.setups.ERoleGroup;
import com.cosmos.models.setups.EUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer id;
	
	private String userFullName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String userPassword;
	
	private String userMobile;
	
	private String userEmail;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private UserTypeDTO userType;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private OrganizationDTO userOrg;
	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"users","roles"} )
	private List<GroupDTO> groups = new ArrayList<>();
	
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Integer> groupsIds;
	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"groups"})
	private List<RoleDTO> roles = new ArrayList<>();
	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"users"})
	private List<ProjectDTO> projects = new ArrayList<>();
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Integer> projectsIds;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer userOrgId;
	
	private String userStatus;
	
	private String userReset;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer userTypeId;


	public UserDTO(EUser eUser,boolean group, boolean project) {
		setId(eUser.getId());
		setUserFullName(eUser.getUserFullName());
		setUserMobile(eUser.getUserMobile());
		setUserEmail(eUser.getUserEmail());
		setUserType(new UserTypeDTO(eUser.getUserType()));
		setUserStatus(eUser.getUserActive());
		setUserReset(eUser.getUserReset());
		setUserOrg(new OrganizationDTO(eUser.getUserOrg()));
		if(group && eUser.getGroups()!=null) {
		addGroups(eUser.getGroups());
		}
		if(project && eUser.getProjects()!=null) {
		addProjects(eUser.getProjects());
		}
		addRoles(eUser.getGroups());
	}
	
	
	public void addRoles(List<EGroupUsers> roleGroups) {
		for(EGroupUsers gu: roleGroups) {
			for(ERoleGroup rg: gu.getEGroup().getRoles()) {
				if(!this.roles.contains(new RoleDTO(rg.getRole(),false,false))) {
			this.roles.add(new RoleDTO(rg.getRole(),false,false));
				}
			}
        
		}
    }
	
	public void addGroups(List<EGroupUsers> userGroups) {
		for(EGroupUsers ug: userGroups) {
        this.groups.add(new GroupDTO(ug.getEGroup(),false,false));
		}
    }
	
	
	public void addProjects(List<EProjectUser> userProjects) {
		for(EProjectUser pu: userProjects) {
        this.projects.add(new ProjectDTO(pu.getProjectUserProject(),false,true));
		}
    }
	
}
