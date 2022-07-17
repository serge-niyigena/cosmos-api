package com.cosmos.models.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "floor_room")
public class EFloorRoom {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "floor_room_id")
    private Integer id;

    @Column(name = "floor_room_value", nullable = false)
    private String floorRoomValue;
 
    @Column(name = "floor_room_desc", nullable = false)
    private String floorRoomDesc;
    
    @Column(name = "floor_room_size", nullable = true)
    private float size;
    
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_room_project_floor_id", referencedColumnName = "project_floor_id")
    private EProjectFloor RoomProjectFloor;
    
	
}
