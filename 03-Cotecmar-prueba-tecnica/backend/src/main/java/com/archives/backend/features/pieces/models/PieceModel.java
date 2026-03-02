package com.archives.backend.features.pieces.models;

import com.archives.backend.enums.State;
import com.archives.backend.features.blocks.models.BlocksModel;
import com.archives.backend.features.projects.models.ProjectModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "piezas")
@Data
public class PieceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "id_pieza")
    private Long idPiece;

    @Column(nullable = false, name = "pieza")
    private String name;

    @Column(name = "peso_teorico")
    private Float theoristWeight;

    @Column(name = "peso_real")
    private Float realWeight;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(optional = true)
    @JoinColumn(name = "Bloque")
    private BlocksModel blocksModel;

    @ManyToOne(optional = true)
    @JoinColumn(name = "Proyecto")
    private ProjectModel projectModel;
}
