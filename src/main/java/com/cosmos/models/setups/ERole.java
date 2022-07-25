package com.cosmos.models.setups;

import java.io.Serializable;
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
@Entity(name = "roles")
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ERole implements Serializable{
	
	private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "role_id")
	    private Integer id;
	    
	    @Column(name = "role_name")
	    private String name;

	    @Column(name = "role_desc")
	    private String desc;
	    
	    @OneToMany(mappedBy = "role")
	    @JsonIgnoreProperties(ignoreUnknown = true, value = "role")
	    @JsonBackReference
	    private List<ERoleGroup> groups;
	
}
