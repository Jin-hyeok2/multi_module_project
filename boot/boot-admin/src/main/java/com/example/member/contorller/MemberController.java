package com.example.member.contorller;

import com.example.dto.SignInForm;
import com.example.dto.SignUpForm;
import com.example.member.response.BaseResponseEntity;
import com.example.member.response.MemberResponse;
import com.example.member.service.BootMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        return BaseResponseEntity.succeed(bootMemberService.signUp(signUpForm));
    }

    @PostMapping("/auth")
    public ResponseEntity<MemberResponse> signIn(@RequestBody @Validated SignInForm signInForm) {
        return ResponseEntity.ok(bootMemberService.signIn(signInForm));
    }
}

