package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.setups.EItemType;

public interface ItemTypeDAO extends JpaRepository<EItemType, Integer> , JpaSpecificationExecutor<EItemType>{

}
