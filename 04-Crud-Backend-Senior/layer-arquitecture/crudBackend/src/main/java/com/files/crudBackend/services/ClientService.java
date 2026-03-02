package com.files.crudBackend.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.files.crudBackend.dtos.Request.CreateClientRequestDto;
import com.files.crudBackend.dtos.Response.CreateClientResponseDto;
import com.files.crudBackend.dtos.Response.ListingAllClientResponseDto;
import com.files.crudBackend.exceptions.ClientNotFoundException;
import com.files.crudBackend.model.ClientModel;
import com.files.crudBackend.repository.ClientRepository;

@Service
public class ClientService {

    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public CreateClientResponseDto createClient(CreateClientRequestDto dto) {
        try {
            Optional<ClientModel> client = repository.findByEmail(dto.email());
            Instant createAt = Instant.now();

            if (!client.isEmpty()) {
                throw new ClientNotFoundException();
            }

            ClientModel newClient = new ClientModel();

            newClient.setName(dto.name());
            newClient.setCellphone(dto.cellphone());
            newClient.setActive(true);
            newClient.setEmail(dto.email());
            newClient.setCreateAt(createAt);

            repository.save(newClient);

            return new CreateClientResponseDto("Client Create succesfull!");

        } catch (Exception e) {
            throw new RuntimeException("Error to create a current Client!");
        }

    }

    public ListingAllClientResponseDto getAll(Boolean delete, Pageable pageable) {

        Page<ClientModel> clients = repository.findByDeleted(delete, pageable);

        return new ListingAllClientResponseDto(clients.getContent(), clients.getNumber(), clients.getSize(),
                clients.getTotalElements(), clients.getTotalPages(), clients.isFirst(), clients.isLast());
    }
}
