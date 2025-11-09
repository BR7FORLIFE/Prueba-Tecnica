package com.archives.backend.features.blocks.models;

import java.util.List;

import com.archives.backend.features.pieces.models.PieceModel;
import com.archives.backend.features.projects.models.ProjectModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bloques")
@Data
public class BlocksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "id_bloque")
    private String blockCode;

    @Column(nullable = false, name = "nombre_bloque")
    private String name;

    // relacion con proyectos
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_proyecto")
    ProjectModel projectModel;

    @OneToMany(mappedBy = "blocksModel")
    private List<PieceModel> pieceModels;
}
