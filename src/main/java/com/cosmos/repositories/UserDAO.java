package com.cosmos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cosmos.models.setups.EUser;


public interface UserDAO extends JpaRepository<EUser, Integer>, JpaSpecificationExecutor<EUser>{

	 @Query(
		        value = "SELECT * FROM users u WHERE u.user_mobile = :contactValue "
		                + "OR c.user_email = :contactValue",
		        nativeQuery = true
		    )
	 Optional<EUser> findMobileOrEmail(String contactValue);
	
}
