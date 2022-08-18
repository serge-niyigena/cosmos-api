package com.cosmos.models.setups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_types")
@Data
@NoArgsConstructor
public class EUserType {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "user_type_id")
	    private Integer id;
	
    @Column(name = "user_type_desc")
    private String desc;

   

    @Column(name = "user_type_name")
    private String name;
}
