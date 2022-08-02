package com.cosmos.models.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.cosmos.models.setups.EProjectStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "project_floors")
@NoArgsConstructor
public class EProjectFloor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "project_floor_id")
    private Integer id;

    @Column(name = "project_floor_value", nullable = false)
    private String projectFloorValue;
 
    @Column(name = "project_floor_ref", nullable = false)
    private String projectFloorRef;
    
    @Column(name = "project_floor_measurement", nullable = true)
    private float projectFloorMeasurement;
    
    @Column(name = "project_floor_description", nullable = true)
    private String projectFloorDescription;
    
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_floor_project_id", referencedColumnName = "project_id")
    private EProject projectFloorProject;
    
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_floor_status", referencedColumnName = "project_status_id" )
    private EProjectStatus projectFloorStatus;


}
