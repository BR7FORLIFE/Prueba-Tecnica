package com.archives.backend.features.projects.dtos.request;

import jakarta.validation.constraints.NotNull;

public record UpdateProjectRequestDto(@NotNull(message = "La id del proyecto no puede ser nulo!") String idProject,
        @NotNull(message = "El nombre del proyecto no puede ser nulo!") String name) {

}
