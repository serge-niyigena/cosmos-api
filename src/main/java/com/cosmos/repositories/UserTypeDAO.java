package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.models.setups.EUserTypes;

public interface UserTypeDAO extends JpaRepository<EUserTypes, Integer>{

}
