package com.minglespace.domain.member;

import com.minglespace.dto.member.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "USER_ROLE")
    private MemberRole role;        // 권한

    @Column(name = "ACCOUNT_ENABLE")
    private boolean enable;         // 계정 활성화 여부

    public Member(String loginId, String password, String username, String email, String nickname, LocalDateTime regDate, MemberRole role, boolean enable) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.regDate = regDate;
        this.role = role;
        this.enable = enable;
    }

    public static Member fromDto(MemberDto dto){
        return new Member(
                dto.getLoginId(),
                dto.getPassword(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getNickname(),
                dto.getRegDate(),
                dto.getRole(),
                true
        );
    }
}
