package com.minglespace.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    USER("ROLE_USER", "일반 사용자 권한"),
    ADMIN("ROLE_ADMIN", "관리자 권한");

    private final String role;
    private final String description;
}
