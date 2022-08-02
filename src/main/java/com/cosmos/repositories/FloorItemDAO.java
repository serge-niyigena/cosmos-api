package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.cosmos.models.project.EFloorItem;


public interface FloorItemDAO extends JpaRepository<EFloorItem, Integer>, JpaSpecificationExecutor<EFloorItem> {
	
	

}
