package com.cosmos.models.setups;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity(name = "organization")
public class EOrganization implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(nullable = false, updatable = false, name = "org_id")
	    private Integer id;

	    @Column(name = "org_name")
	    private String name;
	    
	    @Type(type="org.hibernate.type.BinaryType")
	    @Column(name = "org_logo", nullable = true)
	    private byte[] logo;
	    
	    @Column(name = "org_postal_address", nullable = true)
	    private String postalAddress;
	    
	    @Column(name = "org_physical_address", nullable = true)
	    private String physicalAddress;

	    
	    @Column(name = "org_mobile_number")
	    private String mobileNumber;
	    
	    @Column(name = "org_email")
	    private String email;
}
