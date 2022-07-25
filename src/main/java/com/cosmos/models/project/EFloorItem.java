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
@Entity(name = "project_floor_item")
public class EFloorItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "floor_item_id")
    private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_item_project_floor_id", referencedColumnName = "project_floor_id")
    private EProjectFloor floorItemProjectFloor;
 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_item_item_id", referencedColumnName = "item_id")
    private EItem floorItemItem;
    
    @Column(name = "floor_item_normal_quantity", nullable = false)
    private float floorItemNormalQuantity;
    
    @Column(name = "floor_item_maximum_quantity", nullable = false)
    private float floorItemMaximumQuantity;
    
    @Column(name = "floor_item_quantity_used", nullable = true)
    private float floorItemUsedQuantity;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_item_status", referencedColumnName = "usage_status_id")
    private EUsageStatus floorItemStatus;

    @Column(name = "room_status_report", nullable = true)
    private String floorItemStatusReport;
    
}
