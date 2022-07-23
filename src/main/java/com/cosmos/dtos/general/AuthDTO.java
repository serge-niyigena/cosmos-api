package com.cosmos.dtos.general;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AuthDTO {
	
	@NotBlank
	private String userContact;
	
	@NotBlank
	private String password;

}
