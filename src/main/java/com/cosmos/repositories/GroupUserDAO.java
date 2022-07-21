package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.cosmos.models.setups.EGroupUsers;

public interface GroupUserDAO extends JpaRepository<EGroupUsers, Integer>,JpaSpecificationExecutor<EGroupUsers> {

}
