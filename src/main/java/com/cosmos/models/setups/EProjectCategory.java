package com.cosmos.models.setups;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "project_category")
public class EProjectCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "project_category_id")
	    private Integer id;

	    @Column(name = "project_category_name")
	    private String name;
	    
	    @Column(name = "project_category_desc")
	    private String description;

	
}
