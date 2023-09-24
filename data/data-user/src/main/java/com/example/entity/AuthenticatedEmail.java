package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_emails",
    uniqueConstraints = @UniqueConstraint(columnNames = {"email", "deletedOn"}))
@Where(clause = "DELETED = FALSE")
@SQLDelete(sql = "UPDATE AUTH_EMAILS SET DELETED = TRUE WHERE ID = ?")
public class AuthenticatedEmail extends JpaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String verificationKey;
    @Column(nullable = false)
    private LocalDateTime verifyExpiredAt;


    public String setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
        this.verifyExpiredAt = LocalDateTime.now().plusMinutes(3);
        return verificationKey;
    }
}
