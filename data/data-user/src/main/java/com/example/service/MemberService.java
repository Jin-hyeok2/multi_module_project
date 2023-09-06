package com.example.service;

import com.example.entity.Member;
import com.example.entity.SignUpPlatform;
import com.example.exception.EnumNotFoundException;
import com.example.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member create(String platForm) throws EnumNotFoundException {
        return memberRepository.save(Member.builder()
            .signUpPlatform(SignUpPlatform.fromCode(platForm))
            .build());
    }
}
