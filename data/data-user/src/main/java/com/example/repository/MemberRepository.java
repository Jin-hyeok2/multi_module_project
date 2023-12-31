package com.example.repository;

import com.example.entity.Member;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID>, MemberQueryRepository {

    Optional<Member> findByEmail(String email);
}
