package com.cosmos.dtos.project;

import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.models.setups.EUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Integer id;
	
	private String userFullName;
	
	private String userPassword;
	
	private String userMobile;
	
	private String userEmail;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private UserTypeDTO userType;
	
	private String userStatus;
	
	private String userReset;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer userTypeId;

	public UserDTO(EUser eUser) {
		setId(eUser.getId());
		setUserFullName(eUser.getUserFullName());
		setUserPassword(eUser.getUserPassword());
		setUserMobile(eUser.getUserMobile());
		setUserEmail(eUser.getUserEmail());
		setUserType(new UserTypeDTO(eUser.getEUserType()));
		setUserStatus(eUser.getUserActive());
		setUserReset(eUser.getUserReset());
	}
	
}
