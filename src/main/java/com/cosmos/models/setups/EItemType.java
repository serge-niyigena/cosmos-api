package com.cosmos.models.setups;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "item_types")
@Data
@NoArgsConstructor
public class EItemType implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Column(name = "item_type_desc")
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "item_type_id")
    private Integer id;

    @Column(name = "item_type_name")
    private String name;
    
    @Column(name = "item_selection_type")
    private String iSelectionType;

}
