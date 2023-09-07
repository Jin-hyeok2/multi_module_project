package com.example.contorller;

import com.example.dto.SearchFilter;
import com.example.dto.SignUpForm;
import com.example.dto.response.MemberResponse;
import com.example.service.BootMemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll(SearchFilter searchFilter) {
        return ResponseEntity.ok(bootMemberService.findAll(searchFilter));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody @Validated SignUpForm signUpForm) {
        return ResponseEntity.ok(bootMemberService.signUp(signUpForm));
    }
}

