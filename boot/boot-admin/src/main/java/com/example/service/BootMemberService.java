package com.example.service;

import com.example.dto.SearchFilter;
import com.example.dto.SignUpForm;
import com.example.dto.response.MemberResponse;
import com.example.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BootMemberService {

    private final MemberService memberService;
    private final MemberAuthService memberAuthService;

    public List<MemberResponse> findAll(SearchFilter searchFilter) {
        return memberService.findAll(searchFilter).stream().map(MemberResponse::fromEntity)
            .collect(Collectors.toList());
    }

    public MemberResponse signUp(SignUpForm signUpForm) {
        Member member = memberService.create(signUpForm);
        memberAuthService.createVerificationKey(member);
        return MemberResponse.fromEntity(member);
    }

}