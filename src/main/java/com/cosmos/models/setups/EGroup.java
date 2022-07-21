package com.cosmos.models.setups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "groups")
@NoArgsConstructor
public class EGroup {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "group_id")
	    private Integer id;
	    
	    @Column(name = "group_name")
	    private String name;

	    @Column(name = "group_desc")
	    private String desc;

}
