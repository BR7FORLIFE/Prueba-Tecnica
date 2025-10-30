package com.archives.backend.features.repository.IRepository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.archives.backend.features.models.PieceModel;

@NoRepositoryBean
public interface IPieceRepository extends Repository<PieceModel, Long> {

}
