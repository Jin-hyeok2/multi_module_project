package com.example.member.contorller;

import com.example.member.dto.request.SignInForm;
import com.example.member.dto.request.SignUpForm;
import com.example.member.dto.response.BaseResponseEntity;
import com.example.member.service.BootMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(MemberController.REQUEST_PATH)
public class MemberController {

    final static String REQUEST_PATH = "members";

    private final BootMemberService bootMemberService;

    @PostMapping
    public BaseResponseEntity signUp(@RequestBody @Validated SignUpForm signUpForm) {
        bootMemberService.signUp(
            signUpForm.getEmail(),
            signUpForm.getName(),
            signUpForm.getPassword(),
            signUpForm.getBirth(),
            signUpForm.getPhoneNumber()
        );
        return BaseResponseEntity.succeed();
    }

    @PostMapping("/auth")
    public BaseResponseEntity signIn(@RequestBody @Validated SignInForm signInForm) {
        bootMemberService.signIn(
            signInForm.getEmail(),
            signInForm.getPassword()
        );
        return BaseResponseEntity.succeed();
    }
}

