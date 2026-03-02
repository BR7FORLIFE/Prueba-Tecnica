package com.files.crudBackend.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.files.crudBackend.dtos.Request.CreateClientRequestDto;
import com.files.crudBackend.dtos.Response.CreateClientResponseDto;
import com.files.crudBackend.dtos.Response.ListingAllClientResponseDto;
import com.files.crudBackend.services.ClientService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<CreateClientResponseDto> createClient(@RequestBody @Valid CreateClientRequestDto dto) {
        CreateClientResponseDto response = clientService.createClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ListingAllClientResponseDto> getAll(
            @RequestParam(defaultValue = "true") Boolean delete, Pageable pageable) {
        ListingAllClientResponseDto response = clientService.getAll(delete, pageable);
        return ResponseEntity.ok().body(response);
    }
}
