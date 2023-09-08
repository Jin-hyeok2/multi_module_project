package com.example.service;

import com.example.dto.SignInForm;
import com.example.entity.Member;
import com.example.repository.MemberRepository;
import com.example.repository.expression.MemberQuery;
import com.example.utility.Utility;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberAuthService {

    private final MemberRepository memberRepository;

    public String createVerificationKey(Member member) {
        return member.setVerificationKey(Utility.makeVerificationKey());
    }

    public List<Member> getMembersHasEmail(SignInForm signInForm) {
        return memberRepository.findAll(
            MemberQuery.eqEmail(signInForm.getEmail()));}
}
