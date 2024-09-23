package com.minglespace.service;

import com.minglespace.domain.member.Member;
import com.minglespace.domain.member.MemberRole;
import com.minglespace.domain.member.RoleType;
import com.minglespace.dto.member.MemberDto;
import com.minglespace.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 일반 사용자 회원가입
     */
    @Transactional
    public void join(MemberDto dto){
        // 회원가입 유효성 검사
        if(!validationJoin(dto)) {
            throw new IllegalArgumentException("회원 가입 유효성 검사 오류");
        }

        Member newMember = new Member(
                dto.getLoginId(),
                passwordEncoder.encode(dto.getPassword()),      // 비밀번호 암호화
                dto.getUsername(),
                dto.getEmail(),
                dto.getNickname(),
                LocalDateTime.now(),
                true
        );

        newMember.addRole(RoleType.USER);   // 일반 사용자 권한 추가


        memberRepository.save(newMember);
    }

    // TODO : 유효성 검사 추후 완성
    private boolean validationJoin(MemberDto dto){
        return true;
    }
}
