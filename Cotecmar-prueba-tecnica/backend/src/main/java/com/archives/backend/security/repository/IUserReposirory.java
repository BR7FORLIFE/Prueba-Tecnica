package com.archives.backend.security.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.archives.backend.security.model.UserModel;

@NoRepositoryBean
public interface IUserReposirory extends Repository<UserModel, Long> {
    
}
