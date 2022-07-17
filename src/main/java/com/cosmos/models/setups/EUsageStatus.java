package com.cosmos.models.setups;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "usage_status")
@Data
@NoArgsConstructor
public class EUsageStatus implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Column(name = "usage_status_desc")
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "usage_status_id")
    private Integer id;

    @Column(name = "usage_status_name")
    private String name;
}
