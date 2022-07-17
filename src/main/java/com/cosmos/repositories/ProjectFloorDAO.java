package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.project.EProjectFloor;


public interface ProjectFloorDAO extends JpaRepository<EProjectFloor, Integer>, JpaSpecificationExecutor<EProjectFloor> {

}
