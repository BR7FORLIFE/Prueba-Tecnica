package com.archives.backend.features.projects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archives.backend.features.projects.dtos.request.CreateProjectRequestDto;
import com.archives.backend.features.projects.dtos.request.UpdateProjectRequestDto;
import com.archives.backend.features.projects.services.ProjectService;
import com.archives.backend.shared.utils.helper.ResponseEntityHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(
            @RequestBody @Valid CreateProjectRequestDto dtoProject) {
        var result = projectService.createProject(dtoProject);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @GetMapping
    public ResponseEntity<?> listAllProjects() {
        var result = projectService.listAllProjects();
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @GetMapping("/{idProject}")
    public ResponseEntity<?> listProjectById(@PathVariable String idProject) {
        var result = projectService.listProjectById(idProject);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @PutMapping("/{idProject}")
    public ResponseEntity<?> updateProject(@PathVariable String idProject,
            @RequestBody @Valid UpdateProjectRequestDto dtoProject) {
        var result = projectService.updateProject(idProject, dtoProject);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @DeleteMapping("/{idProject}")
    public ResponseEntity<?> deleteProject(@PathVariable String idProject) {
        var result = projectService.deleteProject(idProject);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    //logica de negocio
    // @PutMapping("/{idProject}/blocks/{blockCode}")
    // public ResponseEntity<?> assignBlockToProject(@PathVariable String idProject, String blockCode){
    //     var result = null;
    //     return ResponseEntityHelper.toResponseEntity(result);
    // }
}
