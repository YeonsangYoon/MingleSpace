package com.minglespace.config;

import com.minglespace.dto.member.MemberDto;
import com.minglespace.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Profile("local")
@Component
public class LocalDataManager {
    private final MemberService memberService;

    @PostConstruct
    public void init(){
        initMember();
    }

    private void initMember(){
        memberService.join(new MemberDto(
                "loginId1",
                "1234",
                "name1",
                "email1",
                "nickname1"
        ));
        memberService.join(new MemberDto(
                "loginId2",
                "1234",
                "name2",
                "email2",
                "nickname2"
        ));
        memberService.join(new MemberDto(
                "loginId3",
                "1234",
                "name3",
                "email3",
                "nickname3"
        ));
        memberService.join(new MemberDto(
                "loginId4",
                "1234",
                "name4",
                "email4",
                "nickname4"
        ));
        memberService.join(new MemberDto(
                "loginId5",
                "1234",
                "name5",
                "email5",
                "nickname5"
        ));

    }
}
