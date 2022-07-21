package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.cosmos.models.setups.ERole;

public interface RoleDAO extends JpaRepository<ERole, Integer>, JpaSpecificationExecutor<ERole> {

}
