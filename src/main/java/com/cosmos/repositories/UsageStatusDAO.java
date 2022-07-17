package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.models.setups.EUsageStatus;

public interface UsageStatusDAO extends JpaRepository<EUsageStatus, Integer>{

}
