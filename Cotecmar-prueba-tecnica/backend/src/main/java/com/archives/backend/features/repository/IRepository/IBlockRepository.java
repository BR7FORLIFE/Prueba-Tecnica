package com.archives.backend.features.repository.IRepository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.archives.backend.features.models.BlocksModel;

@NoRepositoryBean
public interface IBlockRepository extends Repository<BlocksModel, Long>{
    
}
