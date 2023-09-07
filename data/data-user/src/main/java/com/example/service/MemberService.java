package com.example.service;

import com.example.dto.SearchFilter;
import com.example.dto.SignUpForm;
import com.example.entity.Member;
import com.example.entity.MemberRole;
import com.example.repository.MemberRepository;
import com.example.repository.MemberRoleRepository;
import com.example.repository.expression.MemberQuery;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;

    public List<Member> findAll(SearchFilter searchFilter) {
        return memberRepository.findAll(
            MemberQuery.inSignUpPlatform(searchFilter.getSignUpPlatforms())
        );
    }

    public Member create(SignUpForm signUpForm) {
        Member save = memberRepository.save(Member.from(signUpForm));
        memberRoleRepository.saveAll(signUpForm.getRoles()
            .stream().map(role -> MemberRole.from(role, save))
            .collect(Collectors.toList()));
        return save;
    }
}
