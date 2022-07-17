package com.cosmos.dtos.project;

import com.cosmos.dtos.setups.ItemCategoryDTO;
import com.cosmos.dtos.setups.ItemTypeDTO;
import com.cosmos.dtos.setups.UnitTypeDTO;
import com.cosmos.models.project.EItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private Integer id;

    private String name;
 
    private String desc;
    
    private String make;
    
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private ItemCategoryDTO itemCategory;
    
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private UnitTypeDTO itemUnitType;
    
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
    private ItemTypeDTO itemType;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer itemCategoryId;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer itemUnitTypeId;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer itemTypeId;
    
    public ItemDTO(EItem eItem) {
    	setId(eItem.getId());
    	setName(eItem.getName());
    	setDesc(eItem.getDesc());
		setMake(eItem.getMake());;
		setItemCategory(new ItemCategoryDTO(eItem.getItemCategory()));
	    setItemType(new ItemTypeDTO(eItem.getItemType()));
		setItemUnitType(new UnitTypeDTO(eItem.getItemUnitType()));
    	
    }

}
