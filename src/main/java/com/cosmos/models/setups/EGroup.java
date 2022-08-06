package com.cosmos.models.setups;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "groups")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
	   // @JsonIgnoreProperties(ignoreUnknown = true, value = {"group","id"})
	    @JsonIgnore
	    private List<ERoleGroup> roles;
	    
	    @OneToMany(mappedBy = "eGroup", cascade = CascadeType.ALL, orphanRemoval = true)
	   // @JsonIgnoreProperties(ignoreUnknown = true, value = {"id","eGroup"})
	    @JsonIgnore
		 private List<EGroupUsers> users;
	    
	    

}
