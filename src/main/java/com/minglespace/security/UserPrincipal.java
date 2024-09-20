package com.minglespace.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {
    private String id;
    private String loginId;
    private String password;
    private String email;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String id, String loginId, String password, String email, String nickname, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {      // 계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {       // 계정 잠김 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // 비밀번호 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled() {                // 계정 활성화 여부
        return true;
    }
}
