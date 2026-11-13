package com.training.infrastructure;

import com.training.infrastructure.persistence.Adress.AddressJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressJpaEntity, Long> {
}
