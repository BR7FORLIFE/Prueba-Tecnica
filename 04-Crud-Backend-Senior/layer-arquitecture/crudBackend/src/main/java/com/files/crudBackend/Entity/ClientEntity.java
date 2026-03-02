package com.files.crudBackend.Entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;
    private String cellphone;
    private Boolean active;
    private Instant deleteAt;
    private Instant createAt;
    private Instant updateAt;
}
