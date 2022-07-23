package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.setups.EUnitType;

public interface UnitTypeDAO extends JpaRepository<EUnitType, Integer>, JpaSpecificationExecutor<EUnitType>{

}
