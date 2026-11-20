package com.training.infrastructure.repository;

import com.training.infrastructure.persistence.address.AddressJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressJpaEntity, Long> {
}
