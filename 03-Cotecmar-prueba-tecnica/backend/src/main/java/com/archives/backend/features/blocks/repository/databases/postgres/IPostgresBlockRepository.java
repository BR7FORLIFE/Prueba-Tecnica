package com.archives.backend.features.blocks.repository.databases.postgres;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.backend.features.blocks.models.BlocksModel;
import com.archives.backend.features.blocks.repository.IBlockRepository;

@Profile("postgres")
@Repository
public interface IPostgresBlockRepository extends JpaRepository<BlocksModel, Long>, IBlockRepository {

}
