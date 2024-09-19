package com.minglespace.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

}
