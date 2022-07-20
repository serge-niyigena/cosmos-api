package com.cosmos.dtos.project;


import com.cosmos.dtos.setups.UsageStatusDTO;
import com.cosmos.models.project.EFloorItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FloorItemDTO {


	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private Integer id;

	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private ProjectFloorDTO floorItemProjFloor;
 
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private ItemDTO floorItemItem;
    
    
    private float floorItemNormalQuantity;
    
    
    private float floorItemMaximumQuantity;
    
    
    private float floorItemUsedQuantity;
 
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private UsageStatusDTO floorItemStatus;

    
    private String floorItemStatusReport;
    
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer floorItemItemId;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer floorItemProjectFloorId;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer floorItemStatusId;
    
    public FloorItemDTO(EFloorItem eFloorItem) {
    	setId(eFloorItem.getId());
    	setFloorItemMaximumQuantity(eFloorItem.getFloorItemMaximumQuantity());
    	setFloorItemNormalQuantity(eFloorItem.getFloorItemNormalQuantity());
    	setFloorItemUsedQuantity(eFloorItem.getFloorItemUsedQuantity());
    	setFloorItemStatusReport(eFloorItem.getFloorItemStatusReport());
    	setFloorItemItem(new ItemDTO(eFloorItem.getFloorItemItem()));
    	setFloorItemProjFloor(new ProjectFloorDTO(eFloorItem.getFloorItemProjectFloor()));
    	setFloorItemStatus(new UsageStatusDTO(eFloorItem.getFloorItemStatus()));
    }
	
}
