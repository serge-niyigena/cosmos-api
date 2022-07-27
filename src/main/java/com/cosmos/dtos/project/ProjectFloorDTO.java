package com.cosmos.dtos.project;

import com.cosmos.dtos.setups.ProjectStatusDTO;
import com.cosmos.models.project.EProjectFloor;
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
public class ProjectFloorDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private Integer id;

    private String pFloorValue;
 
    private String pFloorRef;
    
    private float pFloorMeasurement;
    
    private String pFloorDescription;
    
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private ProjectDTO pFloorProject;
    
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private ProjectStatusDTO pFloorStatus;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer statusId;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer projectId;
    
    public ProjectFloorDTO(EProjectFloor eProjFloor) {
    	setPFloorDescription(eProjFloor.getProjectFloorDescription());
    	setPFloorMeasurement(eProjFloor.getProjectFloorMeasurement());
    	setPFloorProject(new ProjectDTO(eProjFloor.getProjectFloorProject(),false));
    	setPFloorRef(eProjFloor.getProjectFloorRef());
    	setPFloorStatus(new ProjectStatusDTO(eProjFloor.getProjectFloorStatus()));
    }

}
