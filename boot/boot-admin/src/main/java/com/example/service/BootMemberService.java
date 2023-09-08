package com.example.service;

import com.example.dto.SignInForm;
import com.example.dto.SignUpForm;
import com.example.dto.response.MemberResponse;
import com.example.entity.Member;
import com.example.entity.SignUpPlatform;
import com.example.exception.MemberDuplicateException;
import com.example.exception.MemberNotFoundException;
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