package com.archives.backend.security.repository.databases.sqlite;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.backend.security.model.UserModel;
import com.archives.backend.security.repository.IUserReposirory;

@Profile("sqlite")
@Repository
public interface SqliteUserRepository extends JpaRepository<UserModel, Long>, IUserReposirory {

}
