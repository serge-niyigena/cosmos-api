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

import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EGroupUsers;
import com.cosmos.models.setups.ERoleGroup;
import com.cosmos.models.setups.EUser;

public class SUserDetailsService implements UserDetailsService {

	@Autowired
	private IUser sUser;

    @Override
    public UserDetails loadUserByUsername(String userContact) throws UsernameNotFoundException {

        Optional<EUser> user = sUser.getByMobileOrEmail(userContact);

        if (!user.isPresent()) {
            // TODO: fix response for user not found
            throw new InvalidInputException("invalid credentials provided", "user/password");
        }

       // ERoleGroup eUser = user.get();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (ERoleGroup userRole: eUser.()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRoleId()));
        }

        // converts to org.springframework.security.core.userdetails.UserDetails object
        String password = eUser.getUserPassword();
        UserDetails userDetails = (UserDetails) new User(userContact, password == null ? "" : password, grantedAuthorities);

        return userDetails;
    }


}
