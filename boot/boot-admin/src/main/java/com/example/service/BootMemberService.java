package com.example.service;

import com.example.dto.response.MemberResponse;
import com.example.exception.EnumNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BootMemberService {

    MemberService memberService;

    public List<MemberResponse> findAll() {
        return memberService.findAll().stream().map(MemberResponse::fromEntity)
            .collect(Collectors.toList());
    }

    public MemberResponse create(String platForm) {
        try {
            return MemberResponse.fromEntity(memberService.create(platForm));
        } catch (EnumNotFoundException e) {
            throw new RuntimeException("temp");
        }
    }

}
