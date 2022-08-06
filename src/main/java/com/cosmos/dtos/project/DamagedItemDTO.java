package com.cosmos.dtos.project;

import java.sql.Date;

import com.cosmos.models.project.EDamagedItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DamagedItemDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer damagedId;
	
	private Date damagedDate;
	
	private String damagedDesc;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private FloorItemDTO floorItem;
	
	private Integer damagedQuantity;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer damagedFloorItemId;
	
	public DamagedItemDTO(EDamagedItem eDamagedItem) {
		setDamagedId(eDamagedItem.getId());
		setDamagedDesc(eDamagedItem.getDamagedDesc());
		setDamagedDate(eDamagedItem.getDamagedDate());
		setDamagedQuantity(eDamagedItem.getDamagedQuantity());
		setFloorItem(new FloorItemDTO(eDamagedItem.getFloorItem()));
	}

}
