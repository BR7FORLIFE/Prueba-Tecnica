package com.archives.backend.features.repository.databases.sqlite;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.backend.features.models.PieceModel;
import com.archives.backend.features.repository.IRepository.IPieceRepository;

@Profile("sqlite")
@Repository
public interface ISqlitePieceRepository extends JpaRepository<PieceModel, Long>, IPieceRepository {

}
