package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.models.setups.EItemCategory;

public interface ItemCategoryDAO extends JpaRepository<EItemCategory, Integer>{

}
