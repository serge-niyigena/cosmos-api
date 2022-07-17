package com.cosmos.dtos.project;


import com.cosmos.dtos.setups.UsageStatusDTO;
import com.cosmos.models.project.ERoomItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomItemDTO {


	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private Integer id;

	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private FloorRoomDTO roomItemFloorRoom;
 
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private ItemDTO roomItem;
    
    
    private float itemNormalQuantity;
    
    
    private float itemMaximumQuantity;
    
    
    private float itemUsedQuantity;
 
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private UsageStatusDTO roomItemStatus;

    
    private String statusReport;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer floorRoomId;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer itemId;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer roomItemStatusId;
    
    public RoomItemDTO(ERoomItem eRoomItem) {
    	setItemMaximumQuantity(eRoomItem.getItemMaximumQuantity());
    	setItemNormalQuantity(eRoomItem.getItemNormalQuantity());
    	setItemUsedQuantity(eRoomItem.getItemUsedQuantity());
    	setStatusReport(eRoomItem.getRoomStatusReport());
    	setRoomItem(new ItemDTO(eRoomItem.getRoomItemItem()));
    	setRoomItemFloorRoom(new FloorRoomDTO(eRoomItem.getRoomItemFloorRoom()));
    	setRoomItemStatus(new UsageStatusDTO(eRoomItem.getRoomItemStatus()));
    }
	
}
