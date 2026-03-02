package com.archives.backend.features.pieces.repository.databases.sqlite;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.backend.features.pieces.models.PieceModel;
import com.archives.backend.features.pieces.repository.IPieceRepository;

@Profile("sqlite")
@Repository
public interface ISqlitePieceRepository extends JpaRepository<PieceModel, Long>, IPieceRepository {

}
