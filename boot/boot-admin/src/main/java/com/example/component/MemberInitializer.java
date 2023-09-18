package com.example.component;

import com.example.entity.Role;
import com.example.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberInitializer implements ApplicationRunner {

    private final MemberServiceImpl memberService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberService.create(
            "developer@develop.com",
            "developer",
            "developer",
            "2000.01.01",
            "010-1234-1234",
            Role.DEVELOPER
        );
    }
}
