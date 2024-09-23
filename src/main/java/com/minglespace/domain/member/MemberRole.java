package com.minglespace.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "MEMBER_ROLE")
public class MemberRole {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ROLE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private RoleType roleType;

    public MemberRole(Member member, RoleType roleType) {
        this.member = member;
        this.roleType = roleType;
    }
}
