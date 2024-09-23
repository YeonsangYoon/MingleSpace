package com.minglespace.security;

import com.minglespace.domain.member.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 인증된 사용자인지(로그인) 여부 반환
     * @return 로그인 여부
     */
    public static boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(RoleType.USER.getRole())) {
                return true;
            }
        }

        return false;
    }
}
