package com.cosmos.models.setups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "roles")
@NoArgsConstructor
public class ERole {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "role_id")
	    private Integer id;
	    
	    @Column(name = "role_name")
	    private String name;

	    @Column(name = "role_desc")
	    private String desc;
	
}
