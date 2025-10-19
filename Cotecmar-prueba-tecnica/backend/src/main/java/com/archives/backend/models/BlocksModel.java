package com.archives.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bloques")
@Data
public class BlocksModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, name = "id_bloque")
    private String block_code;

    @Column(nullable = false, name = "nombre_bloque")
    private String name;

    //relacion con proyectos
    @ManyToOne
    @JoinColumn(name = "ID_proyecto")
    ProjectModel projectModel;
}
