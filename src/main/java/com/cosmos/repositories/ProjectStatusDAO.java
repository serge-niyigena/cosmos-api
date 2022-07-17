package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.models.setups.EProjectStatus;

public interface ProjectStatusDAO extends JpaRepository<EProjectStatus, Integer>{

}
