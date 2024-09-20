package com.minglespace.service;

import com.minglespace.domain.member.Member;
import com.minglespace.dto.member.MemberDto;
import com.minglespace.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(MemberDto dto){
        // 회원가입 유효성 검사
        if(!validationJoin(dto)) {
            throw new IllegalArgumentException("회원 가입 유효성 검사 오류");
        }

        // 비밀번호 변환
        String orginPwd = dto.getPassword();
        dto.setPassword(passwordEncoder.encode(orginPwd));

        Member newMember = Member.fromDto(dto);

        memberRepository.save(newMember);
    }

    // TODO : 유효성 검사 추후 완성
    private boolean validationJoin(MemberDto dto){
        return true;
    }
}
