package com.cosmos.models.setups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "users")
public class EUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, name = "user_id")
	private Integer id;
	
	@Column(nullable = false, name = "user_password")
	private String userPassword;
	
	@Column(nullable = false,  name = "user_id")
	private String userFullName;
	
	@Column( name = "user_id")
	private String userMobile;
	
	@Column( name = "user_id")
	private String userEmail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_type", referencedColumnName = "user_type_id")
	private EUserType eUserType;
	
	@Column(nullable = false,  name = "user_id")
	private String userActive;
	
	@Column(nullable = false,  name = "user_id")
	private String userReset;
	

}
