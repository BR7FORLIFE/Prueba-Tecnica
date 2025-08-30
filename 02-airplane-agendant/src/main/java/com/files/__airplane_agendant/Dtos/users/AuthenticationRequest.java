package com.files.__airplane_agendant.Dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotNull(message = "el username no puede ser nulo!")
    private String username;

    @NotNull(message = "La contraseña no puede ser null")
    private String password;

    @NotNull()
    @Email(message = "Debe ser un email valido!")
    private String email;

    @NotNull(message = "La identificacion debe ser valida!")
    @Size(min = 10, max = 10, message = "The identification not have ten digits")
    private String identification;

    @NotNull(message = "El passport no puede ser nulo!")
    private String passport;
}
