package com.cosmos.runners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.cosmos.models.setups.EOrganization;
import com.cosmos.models.setups.EUser;
import com.cosmos.models.setups.EUserType;
import com.cosmos.repositories.UserDAO;
import com.cosmos.services.org.IOrganization;
import com.cosmos.services.project.IUser;
import com.cosmos.services.types.IUserType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminRunner implements CommandLineRunner {

    @Value(value = "${default.value.user.admin-email}")
    private String adminEmailValue;
    
    @Value(value = "${default.value.status.admin-mobile-value}")
    private String adminMobile;

    @Value(value = "${default.value.user.admin-password}")
    private String adminPassword;
    
    @Value(value = "${default.value.status.admin-org-id}")
    private Integer adminOrgId;

    @Value(value = "${default.value.role.admin-group-id}")
    private Integer adminGroupId;
    
    @Value(value = "${default.value.status.admin-type-id}")
    private Integer adminUserTypeId;


   
    @Autowired
    private IUser sUser;
    
    @Autowired
    private IUserType sUserType;
    
    @Autowired
    private IOrganization sOrganization;

    @Autowired
    private UserDAO userDAO;

    @Override
    public void run(String... args) throws Exception {

        String fullname = "system admin";

        log.info(">>> check if admin client exists");
        Optional<EUser> admin = sUser.getByMobileOrEmail(adminEmailValue);

        if (admin.isPresent()) {
            return;
        }
    	
		EUser user = new EUser();
		
		EUserType uType= sUserType.getById(adminUserTypeId,true);
		EOrganization org =sOrganization.getById(adminOrgId, true);
		
		user.setUserFullName(fullname);
		user.setUserPassword(new BCryptPasswordEncoder().encode(adminPassword));
		user.setUserEmail(adminEmailValue);
		user.setUserMobile(adminMobile);
		user.setUserReset("N");
		user.setUserActive("Y");
		user.setUserOrg(org);
		user.setUserType(uType);
		
		user.setGroups(sUser.assignGroups(new ArrayList<Integer>(Arrays.asList(adminGroupId)),false,user));
		
		userDAO.save(user);
		
        log.info("SysAdmin created: id=>{}, fullname=>{}, email=>{}",
            user.getId(),
            String.format("%s", fullname),
            adminEmailValue);
    }

}
