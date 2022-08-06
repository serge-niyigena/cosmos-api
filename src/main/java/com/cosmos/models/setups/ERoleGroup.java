package com.cosmos.models.setups;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "role_groups")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ERoleGroup {
	

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "role_group_id")
	    private Integer id;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "role_group_group_id", referencedColumnName = "group_id")
	    //@JsonIgnoreProperties(value ={"roles"})
	    private EGroup group;

	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="role_group_role_id" , referencedColumnName = "role_id")
	   // @JsonIgnoreProperties(value ={"g"})
	    private ERole role;

}
