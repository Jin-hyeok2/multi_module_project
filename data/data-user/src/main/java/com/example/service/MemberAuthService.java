package com.example.service;

import com.example.entity.Member;
import com.example.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberAuthService {

    public String createVerificationKey(Member member) {
        return member.setVerificationKey(Utility.makeVerificationKey());
    }

}
