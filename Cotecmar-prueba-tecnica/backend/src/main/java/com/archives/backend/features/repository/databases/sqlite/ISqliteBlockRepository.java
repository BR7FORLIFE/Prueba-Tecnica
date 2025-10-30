package com.archives.backend.features.repository.databases.sqlite;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.backend.features.models.BlocksModel;
import com.archives.backend.features.repository.IRepository.IBlockRepository;

@Profile("sqlite")
@Repository
public interface ISqliteBlockRepository extends JpaRepository<BlocksModel, Long>, IBlockRepository {

}
