package com.archives.backend.features.pieces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archives.backend.features.pieces.dtos.request.CreatePieceRequestDto;
import com.archives.backend.features.pieces.dtos.request.UpdatePieceRequestDto;
import com.archives.backend.features.pieces.services.PieceService;
import com.archives.backend.shared.utils.helper.ResponseEntityHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/piece")
public class PieceController {
    @Autowired
    private PieceService pieceService;

    @PostMapping
    public ResponseEntity<?> createPiece(
            @RequestBody @Valid CreatePieceRequestDto dtoPiece) {
        var result = pieceService.createPiece(dtoPiece);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @GetMapping
    public ResponseEntity<?> listAllPieces() {
        var result = pieceService.listAllPieces();
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @GetMapping("/{idPiece}")
    public ResponseEntity<?> listPieceById(@PathVariable Long idPiece) {
        var result = pieceService.listPieceById(idPiece);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @PutMapping("/{idPiece}")
    public ResponseEntity<?> updatePiece(@PathVariable Long idPiece,
            @RequestBody @Valid UpdatePieceRequestDto dtoPiece) {
        var result = pieceService.updatePiece(idPiece, dtoPiece);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @DeleteMapping("/{idPiece}")
    public ResponseEntity<?> deletePiece(@PathVariable Long idPiece) {
        var result = pieceService.deletePiece(idPiece);
        return ResponseEntityHelper.toResponseEntity(result);
    }
}
