package com.cosmos.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.cosmos.models.setups.ERoleGroup;

public interface RoleGroupDAO extends JpaRepository<ERoleGroup, Integer>,JpaSpecificationExecutor<ERoleGroup>{

	@Query(
	        value = "SELECT * FROM role_group rg "
	        		+ " INNER JOIN group_users gu ON gu.group_user_group_id = rg.role_group_group_id "
	        		+ " WHERE gu.group_user_user_id=:userId ",
	        nativeQuery = true
	    )
 ERoleGroup findUserGroupRoles(Integer userId);
	
}
