package com.example.service;

import com.example.component.BootMemberMailer;
import com.example.component.TokenProvider;
import com.example.entity.Member;
import com.example.entity.Role;
import com.example.exception.MemberRollbackException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BootMemberService {

    private final MemberServiceImpl memberServiceImpl;
    private final MemberAuthServiceImpl memberAuthServiceImpl;

    private final TokenProvider tokenProvider;
    private final BootMemberMailer bootMemberMailer;

    public void signUp(
        String email,
        String name,
        String rawPassword,
        String birth,
        String phoneNumber
    ) {
        memberServiceImpl.findOneByEmail(email)
            .ifPresent(MemberRollbackException::duplicated);
        memberServiceImpl.create(
            email,
            name,
            rawPassword,
            birth,
            phoneNumber,
            Role.CUSTOMER
        );

    }

    public void authenticateMailSend(String email) {
        String verificationKey = memberAuthServiceImpl.create(email).getVerificationKey();
        try {
            bootMemberMailer.sendMail(email, "인증코드 발송", verificationKey);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void authenticateMail(String email, String code) {
        memberAuthServiceImpl.findOne(email)
            .ifPresentOrElse(authMail -> {
                if (!authMail.getVerificationKey().equals(code)) {
                    throw MemberRollbackException.notMatchVerificationKey();
                } else {
                    memberServiceImpl.findOneByEmail(email).ifPresent(Member::verify);
                }
            }, MemberRollbackException::emailNotFound);
    }

    public String[] signIn(String email, String rawPassword, Boolean isRemember) {
        Member member = memberServiceImpl.findOneByEmail(email)
            .orElseThrow(MemberRollbackException::emailNotFound);
        if (!memberAuthServiceImpl.checkPassword(member, rawPassword)) {
            throw MemberRollbackException.passwordNotMatch();
        }
        String userSpecification = String.format("%s:%s", member.getId(), member.getRole());
        String accessToken = tokenProvider.createAccessToken(userSpecification);
        String refreshToken = isRemember ? null :
            tokenProvider.createRefreshToken(userSpecification);
        return new String[]{accessToken, refreshToken};
    }
}