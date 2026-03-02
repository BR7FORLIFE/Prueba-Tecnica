package com.archives.backend.features.pieces.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.archives.backend.features.pieces.models.PieceModel;

@NoRepositoryBean
public interface IPieceRepository extends Repository<PieceModel, Long> {

    Optional<PieceModel> findByidPiece(Long idPiece);

    Optional<PieceModel> findByname(String name);

    List<PieceModel> findAll();

    boolean existsByidPiece(Long idPiece);

    boolean existsByname(String name);

    PieceModel save(PieceModel pieceModel);

    void delete(PieceModel pieceModel);
}
