package com.cosmos.dtos.setups;

import com.cosmos.models.setups.EOrganization;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrganizationDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private int id;
	private String name;
	private String email;
	private String mobileNumber;
	private String postalAddress;
	private String physicalAddress;
	
	public OrganizationDTO(EOrganization eOrganization) {
		setId(eOrganization.getId());
		setName(eOrganization.getName());
		setPhysicalAddress(eOrganization.getPhysicalAddress());
		setPostalAddress(eOrganization.getPostalAddress());
		setMobileNumber(eOrganization.getMobileNumber());
		setEmail(eOrganization.getEmail());
	}

}
