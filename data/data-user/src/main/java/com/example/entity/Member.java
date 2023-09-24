package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members", uniqueConstraints = @UniqueConstraint(columnNames = {"email",
    "deletedOn"}))
@Where(clause = "DELETED = FALSE")
@SQLDelete(sql = "UPDATE MEMBERS SET DELETED = TRUE WHERE ID = ?")
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
    // 인증 정보
    private Boolean isVerified;

    @NotNull
    private Role role;

    @PrePersist
    public void prePersist() {
        this.isVerified = role.getAuthorization();
    }

    public void verify() {
        this.isVerified = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.getCode()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.deleted;
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
