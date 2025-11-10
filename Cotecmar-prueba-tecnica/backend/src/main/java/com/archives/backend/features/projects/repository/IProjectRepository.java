package com.archives.backend.features.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.archives.backend.features.projects.models.ProjectModel;

@NoRepositoryBean
public interface IProjectRepository extends Repository<ProjectModel, Long> {

    Optional<ProjectModel> findByid(Long id);

    Optional<ProjectModel> findByidProject(String idProject);

    List<ProjectModel> findAll();

    boolean existsByid(Long id);

    boolean existsByname(String name);

    boolean existsByidProject(String idProject);

    ProjectModel save(ProjectModel projectModel);

    void delete(ProjectModel projectModel);
}
