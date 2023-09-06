package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends JpaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String string;

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//
//    @NotBlank
//    private String email;
    @NotBlank
    private String name;
//    @NotBlank
//    private String password;
//    @NotNull
//    @Pattern(regexp = "^[0-9]{4}\\.[0-9]{2}\\.[0-9]{2}$")
//    private String birth;
//    @NotNull
//    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:[0-9]{3}|[0-9]{4})-[0-9]{4}$")
//    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private SignUpPlatform signUpPlatform;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemberRole> roles;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//            .map(memberRole -> new SimpleGrantedAuthority(memberRole.getRole().getCode()))
//            .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getUsername() {
//        return this.name;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return !this.isDeleted;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
