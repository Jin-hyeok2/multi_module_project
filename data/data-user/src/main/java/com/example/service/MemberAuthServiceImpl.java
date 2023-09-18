package com.example.service;

import com.example.entity.Member;
import com.example.utility.Utility;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberAuthServiceImpl implements UserDetailsService, MemberAuthService<Member> {

    public String createVerificationKey(Member member) {
        return member.setVerificationKey(Utility.makeVerificationKey());
    }

    @Override
    public Boolean checkPassword(Member member, String rawPasswordOrAuthKey) {
        return Utility.comparePassword(rawPasswordOrAuthKey, member.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
