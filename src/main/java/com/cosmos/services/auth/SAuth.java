package com.cosmos.services.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.AuthDTO;
import com.cosmos.dtos.project.UserDTO;
import com.cosmos.dtos.setups.UserTypeDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EUser;
import com.cosmos.services.project.IUser;
import com.cosmos.services.project.SUserDetailsService;
import com.cosmos.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SAuth implements IAuth {
	
	   @Autowired
	   private AuthenticationManager authenticationManager;

	   @Autowired
	   private JwtUtil jwtUtil;

	   @Autowired
	   private IUser sUser;

	   @Autowired
	   private SUserDetailsService sUserDetails;


	    @Override
	    public String authenticateUser(AuthDTO authDTO) {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authDTO.getUserContact(), authDTO.getPassword()));
	        } catch (BadCredentialsException ex) {
	            log.info("{}", ex.getLocalizedMessage());
	            throw new InvalidInputException("invalid credentials provided", "user/password");
	        }

	        UserDetails userDetails = sUserDetails.loadUserByUsername(authDTO.getUserContact());

	        EUser user = sUser.getByMobileOrEmail(authDTO.getUserContact()).get();
	        UserDTO userDtls= new UserDTO(user,true,true);
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("userId", user.getId());
	        claims.put("userOrg",userDtls.getUserOrg());
	        claims.put("userType", userDtls.getUserType());
	        claims.put("userGroups", userDtls.getGroups());
	        //claims.put("userRoles", userDtls.getRoles());
	        claims.put("userName", userDtls.getUserFullName());
	        final String token = jwtUtil.generateToken(userDetails, claims);

	        return token;
	    }

	    @Override
	    public EUser getUser(Integer userId) {
	        Optional<EUser> user = sUser.getById(userId);
	        if (!user.isPresent()) {
	            throw new InvalidInputException("user not found", "userId");
	        }
	        return user.get();
	    }

	   
	
}
