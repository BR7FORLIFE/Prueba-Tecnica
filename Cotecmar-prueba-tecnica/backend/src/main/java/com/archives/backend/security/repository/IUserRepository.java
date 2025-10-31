package com.archives.backend.security.repository;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.archives.backend.security.model.UserModel;

@NoRepositoryBean
public interface IUserRepository extends Repository<UserModel, Long> {
    Optional<UserModel> findByName(String name);

    UserModel save(UserModel userModel);

    boolean existsByName(String name);
}
