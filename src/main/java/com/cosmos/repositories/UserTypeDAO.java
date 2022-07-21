package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.models.setups.EUserType;

public interface UserTypeDAO extends JpaRepository<EUserType, Integer>{

}
