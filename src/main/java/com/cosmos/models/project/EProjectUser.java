package com.cosmos.models.project;

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

import com.cosmos.models.setups.EUser;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "project_users")
@NoArgsConstructor
public class EProjectUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "project_user_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_user_project_id", referencedColumnName = "project_id")
    private EProject projectUserProject;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_user_user_id", referencedColumnName = "project_id")
    private List<EUser> projectUserUsers;
    
    
}
