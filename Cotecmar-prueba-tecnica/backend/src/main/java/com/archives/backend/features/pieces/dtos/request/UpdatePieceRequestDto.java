package com.archives.backend.features.pieces.dtos.request;

import com.archives.backend.enums.State;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdatePieceRequestDto(
                @NotNull(message = "El nombre de la pieza no puede ser nulo!") String name,
                @NotNull(message = "El peso teorico no puede ser nulo") @PositiveOrZero(message = "El peso teorico debe ser positivo") Float theoristWeight,
                @NotNull(message = "El peso real no debe ser nulo!") @PositiveOrZero(message = "el peso real debe ser positivo!") Float realWeight,
                State state) {

}
