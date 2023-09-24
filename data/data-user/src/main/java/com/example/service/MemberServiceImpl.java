package com.example.service;

import com.example.entity.Member;
import com.example.entity.Role;
import com.example.repository.MemberRepository;
import com.example.utility.Utility;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    public Optional<Member> findOneByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void create(
        String email,
        String name,
        String rawPassword,
        String birth,
        String phoneNumber,
        Role role
    ) {
        memberRepository.save(this.of(email, name, rawPassword, birth, phoneNumber, role));
    }

    private Member of(
        String email,
        String name,
        String rawPassword,
        String birth,
        String phoneNumber,
        Role role
    ) {
        return Member.builder()
            .email(email)
            .name(name)
            .password(Utility.encodePassword(rawPassword))
            .birth(Utility.parseDate(birth))
            .phoneNumber(phoneNumber)
            .role(role)
            .build();
    }
}
