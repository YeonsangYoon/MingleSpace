package com.minglespace.dto.member;

import com.minglespace.domain.member.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberDto {
    private String loginId;
    private String password;
    private String username;
    private String email;
    private String nickname;
    private LocalDateTime regDate;
    private MemberRole role;
    private boolean enable;

    public MemberDto(String loginId, String password, String username, String email, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.regDate = LocalDateTime.now(); // 회원가입 시간은 현재 시각
        this.role = MemberRole.USER;        // 일반 user
    }
}
