package com.example.member.service;

import com.example.member.response.MemberResponse;
import com.example.member.exception.MemberDuplicateException;
import com.example.member.exception.MemberNotFoundException;
import com.example.dto.SignInForm;
import com.example.dto.SignUpForm;
import com.example.entity.Member;
import com.example.entity.SignUpPlatform;
import com.example.service.MemberAuthService;
import com.example.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BootMemberService {

    private final MemberService memberService;
    private final MemberAuthService memberAuthService;

    public MemberResponse signUp(SignUpForm signUpForm) {
        memberService.findOne(
                signUpForm.getEmail(),
                signUpForm.getSignUpPlatform())
            .ifPresent(MemberDuplicateException::from);
        Member member = memberService.create(signUpForm);
        memberAuthService.createVerificationKey(member);
        return MemberResponse.from(member);
    }

    public MemberResponse signIn(SignInForm signInForm) {
        List<Member> membersHasEmail = memberAuthService.getMembersHasEmail(signInForm);
        if (membersHasEmail.size() == 0) {
            throw MemberNotFoundException.of();
        }
        Member targetMember;
        for (Member member : membersHasEmail) {
            if (member.getSignUpPlatform().equals(SignUpPlatform.DEFAULT)) {
                targetMember = member;
            }
        }

//        return MemberResponse.from(member);
        return null;
    }
}