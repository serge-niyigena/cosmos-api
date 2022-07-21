package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.setups.ERoleGroup;

public interface RoleGroupDAO extends JpaRepository<ERoleGroup, Integer>,JpaSpecificationExecutor<ERoleGroup>{

}
