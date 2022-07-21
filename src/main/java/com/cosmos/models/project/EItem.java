package com.cosmos.models.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cosmos.models.setups.EItemCategory;
import com.cosmos.models.setups.EItemType;
import com.cosmos.models.setups.EUnitType;

import lombok.Data;

@Data
@Entity(name = "items")
public class EItem {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "item_id")
	    private Integer id;

	    @Column(name = "item_name", nullable = false)
	    private String name;
	 
	    @Column(name = "item_desc", nullable = false)
	    private String desc;
	    
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "item_item_category_id", referencedColumnName = "item_category_id")
	    private EItemCategory itemCategory;
	    
	  
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "item_unit_id", referencedColumnName = "unit_id")
	    private EUnitType itemUnitType;
	    
	  
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "item_item_type_id", referencedColumnName = "item_type_id" )
	    private EItemType itemType;


}
