package com.cosmos.controllers.auth;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cosmos.dtos.general.AuthDTO;
import com.cosmos.responses.SuccessResponse;
import com.cosmos.services.auth.IAuth;

import io.swagger.annotations.Api;

@RestController
@Api("Authentication Endpoints")
public class CAuthentication {
	
	@Autowired
	private IAuth sAuth;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<SuccessResponse> authenticate(@RequestBody AuthDTO authDTO) throws Exception {

		String token = sAuth.authenticateUser(authDTO);

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("token", token);


		
        return ResponseEntity
                .ok()
                .body(new SuccessResponse(200, "Successfully logged in", res));
        }

}
