package com.files.crudBackend.dtos.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record CreateClientRequestDto(@Size(min = 3) String name, @Email String email,
        @Size(min = 7, max = 15) @Null String cellphone) {

}
