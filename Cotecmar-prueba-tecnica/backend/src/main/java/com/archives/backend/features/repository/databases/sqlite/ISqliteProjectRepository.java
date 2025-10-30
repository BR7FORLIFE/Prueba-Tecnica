package com.archives.backend.features.repository.databases.sqlite;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.backend.features.models.ProjectModel;
import com.archives.backend.features.repository.IRepository.IProjectRepository;

@Profile("sqlite")
@Repository
public interface ISqliteProjectRepository extends JpaRepository<ProjectModel, Long>, IProjectRepository {

}
