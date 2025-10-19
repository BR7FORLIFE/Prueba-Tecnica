package com.archives.backend.models;

import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener.RepositoryMethodInvocationResult.State;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "piezas")
public class PieceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, name = "id_pieza")
    private Long id_piece;

    @Column(nullable = false, name = "pieza")
    private String name;

    @Column(name = "peso_teorico")
    private Float theorist_weight;

    @Column(name = "peso_real")
    private Float real_weight;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;
}
