package com.cosmos.models.setups;

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
import com.cosmos.models.project.EProjectUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "users")
public class EUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, name = "user_id")
	private Integer id;
	
	@Column(nullable = false, name = "user_password")
	@JsonIgnore
	private String userPassword;
	
	@Column(nullable = false,  name = "user_fullname")
	private String userFullName;
	
	@Column( name = "user_mobile")
	private String userMobile;
	
	@Column( name = "user_email")
	private String userEmail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_type", referencedColumnName = "user_type_id")
	@JsonIgnore
	private EUserType userType;
	
	@Column(nullable = false,  name = "user_active")
	private String userActive;
	
	@Column(nullable = false,  name = "user_reset")
	private String userReset;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_org_id", referencedColumnName = "org_id")
	@JsonIgnore
	private EOrganization userOrg;

	@OneToMany(mappedBy = "eUsers", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<EGroupUsers> groups;
	
	@OneToMany(mappedBy = "projectUserUsers", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<EProjectUser> projects;
	
	
}
