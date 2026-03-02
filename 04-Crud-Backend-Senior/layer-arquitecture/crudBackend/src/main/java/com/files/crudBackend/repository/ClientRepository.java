package com.files.crudBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.files.crudBackend.Entity.ClientEntity;
import com.files.crudBackend.mapper.ClientMapper;
import com.files.crudBackend.model.ClientModel;
import com.files.crudBackend.repository.postgres.PostgresClientRepositoryInterface;

@Repository
public class ClientRepository {

    private final PostgresClientRepositoryInterface port;

    public ClientRepository(PostgresClientRepositoryInterface postgresClientRepositoryInterface) {
        this.port = postgresClientRepositoryInterface;
    }

    public void save(ClientModel clientModel) {
        port.save(ClientMapper.toEntity(clientModel));
    }

    public Optional<ClientModel> findByEmail(String email) {
        return port.findByEmail(email).map(ClientMapper::toDomain);
    }

    public List<ClientModel> getAll() {
        return port.findAll()
                .stream()
                .map(ClientMapper::toDomain)
                .toList();
    }

    public Page<ClientModel> findByDeleted(Boolean delete, Pageable pageable) {
        Page<ClientEntity> page = delete
                ? port.findByDeleteAtIsNotNull(pageable)
                : port.findByDeleteAtIsNull(pageable);

        return page.map(ClientMapper::toDomain);
    }
}
