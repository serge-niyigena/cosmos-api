package com.cosmos.dtos.project;

import com.cosmos.models.project.EFloorRoom;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FloorRoomDTO {


	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private Integer id;

    private String floorRoomValue;
 
    private String floorRoomDesc;
    
    private float size;
    
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private ProjectFloorDTO RoomProjectFloor;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer projectFloorId;
    
    public FloorRoomDTO(EFloorRoom eFloorRoom) {
    	setFloorRoomDesc(eFloorRoom.getFloorRoomDesc());
    	setFloorRoomValue(eFloorRoom.getFloorRoomValue());
    	setSize(eFloorRoom.getSize());
    	setRoomProjectFloor(new ProjectFloorDTO(eFloorRoom.getRoomProjectFloor()));
    	
    }
	
}
