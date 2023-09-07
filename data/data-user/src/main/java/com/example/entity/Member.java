package com.example.entity;

import com.example.dto.SignUpForm;
import com.example.utility.Utility;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "signUpPlatform", "deletedOn"}))
public class Member extends JpaBaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // 기본 정보
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private LocalDate birth;
    @NotNull
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private SignUpPlatform signUpPlatform;
    // 인증 정보
    @NotNull
    @Builder.Default
    private Boolean isVerified = false;
    private String verificationKey;
    private LocalDateTime verifyExpiredAt;

    @OneToMany(mappedBy = "member")
    private List<MemberRole> roles;

    public static Member from(SignUpForm signUpForm) {
        return Member.builder()
            .email(signUpForm.getEmail())
            .name(signUpForm.getName())
            .password(Utility.encodePassword(signUpForm.getPassword()))
            .birth(Utility.parseDate(signUpForm.getBirth()))
            .phoneNumber(signUpForm.getPhoneNumber())
            .signUpPlatform(SignUpPlatform.fromCode(signUpForm.getSignUpPlatform()))
            .build();
    }

    public String setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
        return verificationKey;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
            .map(memberRole -> new SimpleGrantedAuthority(memberRole.getRole().getCode()))
            .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isVerified;
    }
}
