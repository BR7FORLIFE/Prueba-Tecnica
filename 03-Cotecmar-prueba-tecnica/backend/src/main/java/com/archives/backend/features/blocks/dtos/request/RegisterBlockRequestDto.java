package com.archives.backend.features.blocks.dtos.request;

import jakarta.validation.constraints.NotNull;

public record RegisterBlockRequestDto(@NotNull(message = "El codigo del bloque no puede ser nulo") String blockCode,
        @NotNull(message = "El nombre del bloque no puede ser nulo!") String name) {

}
