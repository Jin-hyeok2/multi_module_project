package com.example.service;

import com.example.dto.SearchFilter;
import com.example.dto.SignUpForm;
import com.example.entity.Member;
import com.example.entity.MemberRole;
import com.example.entity.SignUpPlatform;
import com.example.repository.MemberRepository;
import com.example.repository.MemberRoleRepository;
import com.example.repository.expression.MemberQuery;
import com.example.repository.expression.MemberRoleQuery;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;

    public Optional<Member> findOne(String email, SignUpPlatform signUpPlatform) {
        return memberRepository.findOne(
            MemberQuery.eqEmail(email),
            MemberQuery.inSignUpPlatform(signUpPlatform)
        );
    }

    public Member create(SignUpForm signUpForm) {
        Member save = memberRepository.save(Member.from(signUpForm));
        if (signUpForm.getRoles() == null || signUpForm.getRoles().size() == 0){
            return save;
        }
        memberRoleRepository.saveAll(signUpForm.getRoles()
            .stream().map(role -> MemberRole.from(role, save))
            .collect(Collectors.toList()));
        return save;
    }

    public Optional<Member> findOne(SearchFilter searchFilter) {
        return memberRepository.findOne(
            MemberQuery.eqEmail(searchFilter.getEmail()),
            MemberQuery.eqName(searchFilter.getName()),
            MemberQuery.eqPassword(searchFilter.getPassword()),
            MemberQuery.inSignUpPlatform(searchFilter.getSignUpPlatforms()),
            MemberRoleQuery.eqRole(searchFilter.getRoles())
        );
    }
}
