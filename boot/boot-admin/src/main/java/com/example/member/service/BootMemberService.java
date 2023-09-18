package com.example.member.service;

import com.example.entity.Member;
import com.example.entity.Role;
import com.example.member.exception.MemberException;
import com.example.service.MemberAuthServiceImpl;
import com.example.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BootMemberService {

    private final MemberServiceImpl memberServiceImpl;
    private final MemberAuthServiceImpl memberAuthServiceImpl;

    public void signUp(
        String email,
        String name,
        String rawPassword,
        String birth,
        String phoneNumber
    ) {
        memberServiceImpl.findOneByEmail(email)
            .ifPresent(MemberException::duplicated);
        Member member = memberServiceImpl.create(
            email,
            name,
            rawPassword,
            birth,
            phoneNumber,
            Role.CUSTOMER
        );
        memberAuthServiceImpl.createVerificationKey(member);
    }

    public void signIn(String email, String rawPassword) {
        Member member = memberServiceImpl.findOneByEmail(email)
            .orElseThrow(MemberException::emailNotFound);
        if (!memberAuthServiceImpl.checkPassword(member, rawPassword)) {
            throw MemberException.passwordNotMatch();
        }
    }
}