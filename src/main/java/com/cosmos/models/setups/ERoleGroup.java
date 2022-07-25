package com.cosmos.models.setups;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "role_groups")
@NoArgsConstructor
public class ERoleGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "role_group_id")
	    private Integer id;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "role_group_group_id", referencedColumnName = "group_id")
	    //@JsonIgnoreProperties(value ={"roles"})
	    private EGroup group;

	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name="role_group_role_id" , referencedColumnName = "role_id")
	   // @JsonIgnoreProperties(value ={"g"})
	    private ERole role;

}
