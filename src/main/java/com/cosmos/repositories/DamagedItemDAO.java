package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.project.EDamagedItem;

public interface DamagedItemDAO extends JpaRepository<EDamagedItem, Integer>, JpaSpecificationExecutor<EDamagedItem>{

}
