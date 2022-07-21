package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.cosmos.models.setups.EUser;


public interface UserDAO extends JpaRepository<EUser, Integer>, JpaSpecificationExecutor<EUser>{

}
