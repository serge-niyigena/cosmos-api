package com.cosmos.models.project;

import java.time.LocalDateTime;
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
import javax.persistence.OneToMany;

import com.cosmos.models.setups.EOrganization;
import com.cosmos.models.setups.EProjectCategory;
import com.cosmos.models.setups.EProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "project")
@NoArgsConstructor
public class EProject {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "project_id")
	    private Integer id;

	    @Column(name = "project_name", nullable = false)
	    private String name;
	 
	    @Column(name = "project_desc", nullable = false)
	    private String desc;
	    
	    @Column(name = "project_ref", nullable = true)
	    private String reference;
	    
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "project_status", referencedColumnName = "project_status_id")
	    private EProjectStatus projectStatus;
	    
	  
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "project_org_id", referencedColumnName = "org_id")
	    @JsonIgnore
	    private EOrganization projectOrganization;
	    
	  
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "project_category_id", referencedColumnName = "project_category_id" )
	    @JsonIgnore
	    private EProjectCategory projCategory;

	    
	    @Column(name = "project_creation_date", nullable = false)
	    private LocalDateTime projCreationDate;
	    
	    @Column(name = "project_wef", nullable = false)
	    private LocalDateTime projectWEF;
	    
	    @Column(name = "project_wet", nullable = false)
	    private LocalDateTime projectWET;
	    
	    @Column(name = "project_items_selection_type", nullable = false)
	    private String projectItemSelectionType;
	    
	    @OneToMany(mappedBy = "projectUserProject", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonIgnore
	    private List<EProjectUser> users;
	
}
