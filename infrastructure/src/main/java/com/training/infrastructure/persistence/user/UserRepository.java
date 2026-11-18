package com.training.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    @EntityGraph(attributePaths = "addresses")
    List<UserJpaEntity> findByIdIn(Collection<Long> ids);
}
