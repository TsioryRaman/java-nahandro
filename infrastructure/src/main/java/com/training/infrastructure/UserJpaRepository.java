package com.training.infrastructure;

import com.training.infrastructure.persistence.User.UserJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    @Query("""
    SELECT DISTINCT u
            FROM UserJpaEntity u
            LEFT JOIN FETCH u.addresses \s
""")
    Page<UserJpaEntity> findAllWithAddresses(Pageable pageable);

    Page<UserJpaEntity> findByEmailContainingIgnoreCaseOrUsernameContainingIgnoreCase(String email, String username, Pageable pageable);

    Optional<UserJpaEntity> findByEmail(String email);
}
