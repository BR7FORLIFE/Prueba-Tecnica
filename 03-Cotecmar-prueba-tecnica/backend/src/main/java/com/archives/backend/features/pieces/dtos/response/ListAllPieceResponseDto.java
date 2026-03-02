package com.archives.backend.features.pieces.dtos.response;

import java.util.List;

import com.archives.backend.features.pieces.dtos.PieceDto;

public record ListAllPieceResponseDto(String message, List<PieceDto> pieces) {

}
