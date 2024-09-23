package com.minglespace.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "LOGIN_ID", unique = true)
    private String loginId;         // Login id

    @Column(name = "PASSWORD")
    private String password;        // 비밀번호

    @Column(name = "USERNAME")
    private String username;        // 이름

    @Column(name = "EMAIL")
    private String email;           // 이메일

    @Column(name = "NICKNAME")
    private String nickname;        // 닉네임

    @Column(name = "REG_DATE")
    private LocalDateTime regDate;  // 등록일

    @Column(name = "ACCOUNT_ENABLE")
    private boolean enable;         // 계정 활성화 여부

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<MemberRole> roles = new ArrayList<>();

    public Member(String loginId, String password, String username, String email, String nickname, LocalDateTime regDate, boolean enable) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.regDate = regDate;
        this.enable = enable;
    }

    /**
     * 권한 보유 여부
     */
    public boolean hasRole(RoleType roleType){
        for(MemberRole role : this.roles){
            if(role.getRoleType() == roleType){
                return true;
            }
        }
        return false;
    }

    /**
     * 권한 추가
     */
    public void addRole(RoleType roleType){
        if(hasRole(roleType)) return;

        MemberRole newRole = new MemberRole(this, roleType);
        this.roles.add(newRole);
    }
}
