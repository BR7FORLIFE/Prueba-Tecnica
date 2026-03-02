package com.archives.backend.features.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.archives.backend.features.projects.models.ProjectModel;

@NoRepositoryBean
public interface IProjectRepository extends Repository<ProjectModel, Long> {

    Optional<ProjectModel> findByid(Long id);

    Optional<ProjectModel> findByidProject(String idProject);

    @Query("SELECT p FROM ProjectModel p LEFT JOIN FETCH p.blocksModels WHERE p.idProject = :id")
    Optional<ProjectModel> findProjectWithBlocks(@Param("id") String idProject);

    List<ProjectModel> findAll();

    boolean existsByid(Long id);

    boolean existsByname(String name);

    boolean existsByidProject(String idProject);

    ProjectModel save(ProjectModel projectModel);

    void delete(ProjectModel projectModel);
}
