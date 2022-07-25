package com.cosmos.dtos.project;

import java.time.LocalDateTime;
import java.util.List;

import com.cosmos.dtos.setups.OrganizationDTO;
import com.cosmos.dtos.setups.ProjectCategoryDTO;
import com.cosmos.dtos.setups.ProjectStatusDTO;
import com.cosmos.dtos.setups.RoleGroupDTO;
import com.cosmos.models.project.EProject;
import com.cosmos.models.project.EProjectUser;
import com.cosmos.models.setups.ERoleGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ProjectDTO {
	
	    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
		private Integer id;

	    private String name;
	    private String desc;
	    private String reference;
	    private LocalDateTime creationDate;
	   
	    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	    private ProjectStatusDTO projStatus;
	    
	    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	    private OrganizationDTO projOrgnanization;
	    
	    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	    private ProjectCategoryDTO projectCategory;
	    
	    private LocalDateTime projWef;
	    private LocalDateTime projWet;
	    private String projectItemSelectionType;
	    
	    @JsonProperty(access = Access.WRITE_ONLY)
	    private Integer organizationId;
	    
	    @JsonProperty(access = Access.WRITE_ONLY)
	    private Integer statusId;
	    
	    @JsonProperty(access = Access.WRITE_ONLY)
	    private Integer categoryId;
	    
	    private List<ProjectUserDTO> users;
	    
	    public ProjectDTO(EProject eProject) {
	    	setId(eProject.getId());
	    	setName(eProject.getName());
	    	setDesc(eProject.getDesc());
	    	setReference(eProject.getReference());
	    	setCreationDate(eProject.getProjCreationDate());
	    	setProjStatus(new ProjectStatusDTO(eProject.getProjectStatus()));
	    	setProjOrgnanization(new OrganizationDTO(eProject.getProjectOrgnanization()));
	    	setProjectCategory(new ProjectCategoryDTO(eProject.getProjCategory()));
	    	setProjWef(eProject.getProjectWEF());
	    	setProjWet(eProject.getProjectWET());
	    	setProjectItemSelectionType(eProject.getProjectItemSelectionType());
	    	
	    }
	    
	    public void addUsers(List<EProjectUser> projectUsers) {
			for(EProjectUser pu: projectUsers) {
	        this.users.add(new ProjectUserDTO(pu));
			}
	    }
	
}
