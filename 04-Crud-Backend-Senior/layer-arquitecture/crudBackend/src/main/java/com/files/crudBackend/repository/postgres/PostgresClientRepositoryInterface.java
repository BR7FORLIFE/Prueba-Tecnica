package com.files.crudBackend.repository.postgres;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.files.crudBackend.Entity.ClientEntity;

public interface PostgresClientRepositoryInterface extends JpaRepository<ClientEntity, UUID> {
    Optional<ClientEntity> findByEmail(String email);

    ClientEntity save(ClientEntity entity);

    List<ClientEntity> findAll();

    Page<ClientEntity> findByDeleteAtIsNotNull(Pageable pageable);

    Page<ClientEntity> findByDeleteAtIsNull(Pageable pageable);

}
