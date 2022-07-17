package com.cosmos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.cosmos.models.project.EFloorRoom;

public interface FloorRoomDAO extends JpaRepository<EFloorRoom, Integer>, JpaSpecificationExecutor<EFloorRoom> {

}
