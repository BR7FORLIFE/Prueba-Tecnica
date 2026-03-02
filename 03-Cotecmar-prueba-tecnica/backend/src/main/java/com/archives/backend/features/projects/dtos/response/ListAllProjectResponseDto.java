package com.archives.backend.features.projects.dtos.response;

import java.util.List;

import com.archives.backend.features.projects.dtos.ProjectDto;

public record ListAllProjectResponseDto(String message, List<ProjectDto> projects) {
    
}
