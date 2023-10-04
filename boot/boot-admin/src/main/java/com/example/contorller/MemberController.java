package com.example.contorller;

import com.example.dto.request.SignInForm;
import com.example.dto.request.SignUpForm;
import com.example.dto.response.BaseResponse;
import com.example.dto.response.BaseResponseEntity;
import com.example.service.BootMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
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
        MultiValueMap<String, String> headers = new HttpHeaders();
        String[] tokens = bootMemberService.signIn(
            signInForm.getEmail(),
            signInForm.getPassword(),
            signInForm.getIsRemember()
        );
        headers.add("Set-Cookie", String.format("%s=Bearer %s", "accessToken", tokens[0]));
        return BaseResponseEntity.succeed(new BaseResponse(tokens[1]), headers);
    }
}

