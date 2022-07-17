package com.cosmos.models.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.cosmos.models.setups.EUsageStatus;

import lombok.Data;

@Data
@Entity(name = "room_item")
public class ERoomItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "room_item_id")
    private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_item_floor_room_id", referencedColumnName = "floor_room_id")
    private EFloorRoom roomItemFloorRoom;
 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_item_item_id", referencedColumnName = "item_id")
    private EItem roomItemItem;
    
    @Column(name = "room_item_normal_quantity", nullable = false)
    private float itemNormalQuantity;
    
    @Column(name = "room_item_maximum_quantity", nullable = false)
    private float itemMaximumQuantity;
    
    @Column(name = "room_item_quantity_used", nullable = true)
    private float itemUsedQuantity;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_item_status", referencedColumnName = "usage_status_id")
    private EUsageStatus roomItemStatus;

    @Column(name = "room_status_report", nullable = true)
    private String roomStatusReport;
    
}
