package com.minglespace.security;

import com.minglespace.domain.member.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * password encoder : BCrypt password encoder 사용
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Security 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http basic 인증 비활성화
        http.httpBasic(AbstractHttpConfigurer::disable);

        // csrf 비활성화
        http.csrf(AbstractHttpConfigurer::disable);

        // session 비활성화
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // http 인증 설정
        String[] permitAllWhiteList = {
                "/",
                "/login"
        };
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(permitAllWhiteList).permitAll()          // 허용 URL
                .requestMatchers("/admin/**").hasAuthority(MemberRole.ADMIN.getRole())  // 관리자 URL
                .anyRequest().authenticated()
        );

        // login 설정
        http.formLogin(formLogin -> formLogin
                .loginProcessingUrl("/login")
                .successForwardUrl("/")
        );

        // logout 설정
        http.logout(logout -> logout
                .logoutUrl("/logout")
        );

        return http.build();
    }
}
