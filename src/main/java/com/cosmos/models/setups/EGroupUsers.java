package com.cosmos.models.setups;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@JoinColumn(name = "role_group_group_id", referencedColumnName = "group_id")
    private EGroup eGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_group_user_id", referencedColumnName = "user_id")
    private EUser eUsers;

}
