package com.example.service;

import com.example.entity.AuthenticatedEmail;
import com.example.entity.Member;
import com.example.repository.AuthenticatedEmailRepository;
import com.example.repository.expression.AuthenticatedEmailQuery;
import com.example.utility.Utility;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberAuthServiceImpl implements UserDetailsService, MemberAuthService<Member> {

    private final AuthenticatedEmailRepository authenticatedEmailRepository;

    public AuthenticatedEmail create(String email) {

        return authenticatedEmailRepository.save(
            AuthenticatedEmail.builder()
                .email(email)
                .verificationKey(Utility.makeVerificationKey())
                .build());
    }

    public Optional<AuthenticatedEmail> findOne(String email) {
        return authenticatedEmailRepository.findOne(
            AuthenticatedEmailQuery.eqEmail(email),
            AuthenticatedEmailQuery.notExpired()
        );
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
