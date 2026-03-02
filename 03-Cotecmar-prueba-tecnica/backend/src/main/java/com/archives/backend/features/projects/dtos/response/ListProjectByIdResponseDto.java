package com.archives.backend.features.projects.dtos.response;

import com.archives.backend.features.projects.dtos.ProjectDto;

public record ListProjectByIdResponseDto(String message, ProjectDto project) {
    
}
