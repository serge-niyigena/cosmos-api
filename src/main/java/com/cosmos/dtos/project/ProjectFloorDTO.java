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
public class ProjectFloorDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private Integer id;

	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"projStatus","projOrganization","projectCategory"})
	private ProjectDTO floorProject;
	    
	@JsonProperty(access = Access.READ_ONLY)
	private ProjectStatusDTO floorStatus;
	
    private String floorValue;
 
    private String floorRef;
    
    private float floorMeasurement;
    
    private String floorDescription;
    
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer statusId;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer projectId;
    
    
    public ProjectFloorDTO(EProjectFloor eProjFloor) {
    	setId(eProjFloor.getId());
    	setFloorDescription(eProjFloor.getProjectFloorDescription());
    	setFloorMeasurement(eProjFloor.getProjectFloorMeasurement());
    	setFloorProject(new ProjectDTO(eProjFloor.getProjectFloorProject(),false,false));
    	setFloorRef(eProjFloor.getProjectFloorRef());
    	setFloorValue(eProjFloor.getProjectFloorValue());
    	setFloorStatus(new ProjectStatusDTO(eProjFloor.getProjectFloorStatus()));
    }

}
