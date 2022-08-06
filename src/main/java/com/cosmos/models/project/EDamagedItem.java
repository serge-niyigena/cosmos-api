package com.cosmos.models.project;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "damaged_items")
@NoArgsConstructor
public class EDamagedItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "damaged_id")
	private Integer id;
	
	@Column(nullable = false, name = "damaged_date")
	private Date damagedDate;
	
	@Column(nullable = false, updatable = false, name = "damaged_desc")
	private String damagedDesc;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "damaged_floor_item_id", referencedColumnName = "floor_item_id")
	private EFloorItem floorItem;
	
	private Integer DamagedQuantity;
	

}
