package com.archives.backend.security.dtos.request;

import jakarta.validation.constraints.NotNull;

public record LoginUserRequestDto(@NotNull(message = "el nombre de usuario no puede ser nulo!") String username,
        @NotNull(message = "El password no debe ser nulo!") String password) {

}
