package com.cosmos.models.setups;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "groups")
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class EGroup {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "group_id")
	    private Integer id;
	    
	    @Column(name = "group_name")
	    private String name;

	    @Column(name = "group_desc")
	    private String desc;
	    
	    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonIgnoreProperties(value ="group")
	    @JsonBackReference
	    private List<ERoleGroup> roles;
	    
	 // @JsonIgnoreProperties(value ={"eGroup"})
	    @OneToMany(mappedBy = "eUsers", cascade = CascadeType.ALL, orphanRemoval = true)
		 private List<EGroupUsers> users;
	    
	    

}
