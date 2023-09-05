package com.example.contorller;

import com.example.dto.response.MemberResponse;
import com.example.service.BootMemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final BootMemberService bootMemberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        return ResponseEntity.ok(bootMemberService.findAll());
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestParam String platform) {
        return ResponseEntity.ok(bootMemberService.create(platform));
    }
}
