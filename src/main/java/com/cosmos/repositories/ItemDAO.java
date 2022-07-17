package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cosmos.models.project.EItem;


public interface ItemDAO extends JpaRepository<EItem, Integer>, JpaSpecificationExecutor<EItem>  {

}
