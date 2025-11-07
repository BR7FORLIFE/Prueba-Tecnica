package com.archives.backend.security.dtos.request;

import jakarta.validation.constraints.NotNull;

public record RegisterUserRequestDto(@NotNull(message = "El username no puede ser nulo!") String username,
        @NotNull(message = "El password no puede ser nulo!") String password) {

}
