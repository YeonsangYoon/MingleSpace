package com.minglespace.security;

import com.minglespace.domain.member.Member;
import com.minglespace.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    /**
     * Login Id를 통해 UserDetails 정보 조회
     * @param username Login Id
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(username)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(username + "은 존재하지 않는 아이디입니다."));
    }

    private UserDetails createUser(Member member){
        List<GrantedAuthority> authorities = new ArrayList<>();

        member.getRoles().forEach(memberRole -> {
            authorities.add(new SimpleGrantedAuthority(memberRole.getRoleType().getRole()));
        });

        return new UserPrincipal(
                member.getId().toString(),
                member.getLoginId(),
                member.getPassword(),
                member.getEmail(),
                member.getNickname(),
                authorities
        );
    }
}
