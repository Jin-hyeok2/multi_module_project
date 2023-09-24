package com.example.repository;

import com.example.entity.AuthenticatedEmail;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticatedEmailRepository extends JpaRepository<AuthenticatedEmail, UUID>,
    AuthenticatedEmailQueryRepository {

    Optional<AuthenticatedEmail> findByEmail(String email);
}
