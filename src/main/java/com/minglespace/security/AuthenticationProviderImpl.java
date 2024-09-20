package com.minglespace.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 인증 제공자 : 로그인 인증 수행 및 인증 객체 반환
 */
@RequiredArgsConstructor
@Component("authenticationProvider")
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    /**
     * 로그인 인증 구현
     * @param authentication 인증되지 않은 unauthentication 객체
     * @return Authentication 인증 완료된 authentication 객체
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 회원 조회
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("잘못된 비밀번호 입니다.");
        }

        // 인증완료된 Authentication 객체 반환
        return UsernamePasswordAuthenticationToken.authenticated(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
