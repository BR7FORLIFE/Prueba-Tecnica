package com.archives.backend.features.projects.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archives.backend.features.blocks.models.BlocksModel;
import com.archives.backend.features.blocks.repository.IBlockRepository;
import com.archives.backend.features.projects.dtos.ProjectDto;
import com.archives.backend.features.projects.dtos.request.CreateProjectRequestDto;
import com.archives.backend.features.projects.dtos.request.UpdateProjectRequestDto;
import com.archives.backend.features.projects.dtos.response.AssignBlockToProjectResponseDto;
import com.archives.backend.features.projects.dtos.response.CreateProjectResponseDto;
import com.archives.backend.features.projects.dtos.response.DeleteProjectResponseDto;
import com.archives.backend.features.projects.dtos.response.ListAllProjectResponseDto;
import com.archives.backend.features.projects.dtos.response.ListProjectByIdResponseDto;
import com.archives.backend.features.projects.dtos.response.UpdateProjectResponseDto;
import com.archives.backend.features.projects.models.ProjectModel;
import com.archives.backend.features.projects.repository.IProjectRepository;
import com.archives.backend.shared.utils.result.Result;

@Service
public class ProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IBlockRepository blockRepository;

    public Result<CreateProjectResponseDto, Exception> createProject(CreateProjectRequestDto dtoProject) {
        boolean existProject = projectRepository.existsByname(dtoProject.name());

        if (existProject) {
            return Result.error(new Exception("the current project already exists!"));
        }

        ProjectModel projectModel = new ProjectModel();
        projectModel.setIdProject(dtoProject.idProject());
        projectModel.setName(dtoProject.name());

        projectRepository.save(projectModel);

        CreateProjectResponseDto response = new CreateProjectResponseDto(dtoProject.idProject(),
                "project created succesfull!");

        return Result.ok(response);
    }

    public Result<ListAllProjectResponseDto, Exception> listAllProjects() {
        try {
            List<ProjectDto> projectDtos = projectRepository.findAll().stream().map(project -> {
                ProjectDto projectDto = new ProjectDto(project.getIdProject(), project.getName());
                return projectDto;
            }).toList();

            ListAllProjectResponseDto response = new ListAllProjectResponseDto("projects get succesfull!", projectDtos);

            return Result.ok(response);

        } catch (Exception e) {
            return Result.error(new Exception("Error to get All projects!"));
        }
    }

    public Result<ListProjectByIdResponseDto, Exception> listProjectById(String idProject) {
        try {
            return projectRepository.findByidProject(idProject).map(project -> {

                ProjectDto projectDto = new ProjectDto(project.getIdProject(), project.getName());

                ListProjectByIdResponseDto response = new ListProjectByIdResponseDto("Project get succesfull!",
                        projectDto);

                return Result.ok(response);

            }).orElseGet(() -> Result.error(new Exception("The current project doesnt exist!")));

        } catch (Exception e) {
            return Result.error(new Exception("Error to get project by id!"));
        }
    }

    public Result<UpdateProjectResponseDto, Exception> updateProject(String idProject,
            UpdateProjectRequestDto dtoProject) {
        try {
            return projectRepository.findByidProject(idProject).map(project -> {

                project.setIdProject(dtoProject.idProject());
                project.setName(dtoProject.name());

                projectRepository.save(project);

                UpdateProjectResponseDto response = new UpdateProjectResponseDto(dtoProject.idProject(),
                        "Project update succesfull!");

                return Result.ok(response);

            }).orElseGet(() -> Result.error(new Exception("The current project doesnt exists!")));

        } catch (Exception e) {
            return Result.error(new Exception("Error to update the current project!"));
        }
    }

    public Result<DeleteProjectResponseDto, Exception> deleteProject(String idProject) {
        try {
            return projectRepository.findByidProject(idProject).map(project -> {
                projectRepository.delete(project);

                DeleteProjectResponseDto response = new DeleteProjectResponseDto(project.getIdProject(),
                        "Project deleted succesfull!");

                return Result.ok(response);

            }).orElseGet(() -> Result.error(new Exception("The project doesnt exists!")));

        } catch (Exception e) {
            return Result.error(new Exception("Error to delete current project!"));
        }
    }

    public Result<AssignBlockToProjectResponseDto, Exception> assignBlockToProject(String idProject, String blockCode) {

        boolean existProject = projectRepository.existsByidProject(idProject);
        boolean existBlock = blockRepository.existsByblockCode(blockCode);

        if (!existProject) {
            return Result.error(new Exception("The project doesnt exists!"));
        }

        if (!existBlock) {
            return Result.error(new Exception("the block doesnt exist!"));
        }

        ProjectModel project = projectRepository.findByidProject(idProject)
                .orElseThrow(() -> new IllegalArgumentException("Error to get the project"));

        BlocksModel model = blockRepository.findByBlockCode(blockCode)
                .orElseThrow(() -> new IllegalArgumentException("Error to get the block"));

        project.setBlocksModels(new ArrayList<>(List.of(model)));
        projectRepository.save(project);

        AssignBlockToProjectResponseDto response = new AssignBlockToProjectResponseDto(idProject, blockCode,
                "Assign succesfull!");

        return Result.ok(response);
    }
}
