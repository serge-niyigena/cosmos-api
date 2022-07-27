package com.cosmos.models.setups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "group_users")
@NoArgsConstructor
public class EGroupUsers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "group_user_id")
    private Integer id;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_user_group_id", referencedColumnName = "group_id")
    private EGroup eGroup;

	//@JsonIgnoreProperties(ignoreUnknown = true, value = {"groups","projects"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_user_user_id", referencedColumnName = "user_id")
    private EUser eUsers;

}
