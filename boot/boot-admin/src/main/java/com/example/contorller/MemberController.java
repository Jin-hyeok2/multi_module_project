package com.example.contorller;

import com.example.dto.SignInForm;
import com.example.dto.SignUpForm;
import com.example.dto.response.MemberResponse;
import com.example.service.BootMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
    public ResponseEntity<MemberResponse> signUp(@RequestBody @Validated SignUpForm signUpForm) {
        return ResponseEntity.ok(bootMemberService.signUp(signUpForm));
    }

    @PostMapping("/auth")
    public ResponseEntity<MemberResponse> signIn(@RequestBody @Validated SignInForm signInForm) {
        return ResponseEntity.ok(bootMemberService.signIn(signInForm));
        }
}

