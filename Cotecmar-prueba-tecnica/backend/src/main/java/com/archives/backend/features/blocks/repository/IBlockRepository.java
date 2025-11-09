package com.archives.backend.features.blocks.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.archives.backend.features.blocks.models.BlocksModel;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IBlockRepository extends Repository<BlocksModel, Long> {

    Optional<BlocksModel> findById(Long id);

    Optional<BlocksModel> findByBlockCode(String blockCode);

    boolean existsById(Long id);

    boolean existsByBlockCode(String blockCode);

    BlocksModel save(BlocksModel blocksModel);

    List<BlocksModel> findAll();

    void delete(BlocksModel blocksModel);
}
