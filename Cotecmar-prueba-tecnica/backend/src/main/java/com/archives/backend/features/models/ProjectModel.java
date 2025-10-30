package com.archives.backend.features.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "proyectos")
@Data
public class ProjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "id_proyecto")
    private String id_projects;

    @Column(nullable = false, name = "nombre")
    private String name;

    //relacion con bloques
    @OneToMany(mappedBy = "projectModel", cascade = CascadeType.ALL)
    List<BlocksModel> blocksModels;

    @OneToMany(mappedBy = "projectModel")
    private List<PieceModel> pieceModels;
}
