package com.archives.backend.features.blocks.dtos.request;

import jakarta.validation.constraints.NotNull;

public record UpdateBlockRequestDto(@NotNull(message = "El nombre del bloque no puede ser nulo!") String name) {

}
