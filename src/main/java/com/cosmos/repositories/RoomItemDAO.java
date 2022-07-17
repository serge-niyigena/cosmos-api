package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.cosmos.models.project.ERoomItem;


public interface RoomItemDAO extends JpaRepository<ERoomItem, Integer>, JpaSpecificationExecutor<ERoomItem> {

}
