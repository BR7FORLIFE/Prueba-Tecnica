package com.archives.backend.features.pieces.dtos;

import com.archives.backend.enums.State;

public record PieceDto(Long idPiece, String name, Float theoristWeight, Float realWeight, State state) {

}
