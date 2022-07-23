package com.cosmos.services.auth;

import com.cosmos.dtos.general.AuthDTO;
import com.cosmos.models.setups.EUser;

public interface IAuth {

	
	String authenticateUser(AuthDTO authDTO);
  
    EUser getUser(Integer userId);

	
}
