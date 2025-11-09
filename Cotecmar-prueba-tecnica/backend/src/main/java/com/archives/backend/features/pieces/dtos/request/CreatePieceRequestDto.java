package com.archives.backend.features.pieces.dtos.request;

import com.archives.backend.enums.State;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePieceRequestDto(
        @NotNull(message = "la id de la pieza no puede ser nulo!") @Positive(message = "La id de la pieza debe ser positivo!") Long idPiece,
        @NotNull(message = "El nombre de la pieza no puede ser nulo!") String name,
        @NotNull(message = "El peso teorico no puede ser nulo") @Positive(message = "El peso teorico debe ser positivo") Float theoristWeight,
        @NotNull(message = "El peso real debe ser positivo!") Float realWeight, State state) {

}
