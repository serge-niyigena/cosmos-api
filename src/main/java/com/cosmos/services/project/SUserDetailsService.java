package com.cosmos.services.project;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.project.UserDTO;
import com.cosmos.dtos.setups.RoleDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EUser;

@Service
public class SUserDetailsService implements UserDetailsService {

	@Autowired
	private IUser sUser;
	

    @Override
    public UserDetails loadUserByUsername(String userContact) throws UsernameNotFoundException {

        Optional<EUser> user = sUser.getByMobileOrEmail(userContact);
        UserDTO userDtls= new UserDTO(user.get(),true,true);

       // ERoleGroup roleGroup= roleGroupDAO.findUserGroupRoles(user.get().getId());
        if (!user.isPresent()) {
            // TODO: fix response for user not found
            throw new InvalidInputException("invalid credentials provided", "user/password");
        }

      
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleDTO role: userDtls.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        // converts to org.springframework.security.core.userdetails.UserDetails object
        String password = user.get().getUserPassword();
        UserDetails userDetails = (UserDetails) new User(userContact, password == null ? "" : password, grantedAuthorities);

        return userDetails;
    }


}
