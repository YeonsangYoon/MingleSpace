package com.minglespace.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "TEST")
public class TestEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
}
