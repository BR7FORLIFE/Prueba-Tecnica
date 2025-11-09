package com.archives.backend.features.pieces.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archives.backend.features.pieces.dtos.PieceDto;
import com.archives.backend.features.pieces.dtos.request.CreatePieceRequestDto;
import com.archives.backend.features.pieces.dtos.request.UpdatePieceRequestDto;
import com.archives.backend.features.pieces.dtos.response.CreatePieceResponseDto;
import com.archives.backend.features.pieces.dtos.response.DeletePieceReponseDto;
import com.archives.backend.features.pieces.dtos.response.ListAllPieceResponseDto;
import com.archives.backend.features.pieces.dtos.response.ListPieceByIdResponseDto;
import com.archives.backend.features.pieces.dtos.response.UpdatePieceResponseDto;
import com.archives.backend.features.pieces.models.PieceModel;
import com.archives.backend.features.pieces.repository.IPieceRepository;
import com.archives.backend.shared.utils.result.Result;

@Service
public class PieceService {

    @Autowired
    private IPieceRepository pieceRepository;

    public Result<CreatePieceResponseDto, Exception> createPiece(CreatePieceRequestDto dtoPiece) {
        boolean existsPiece = pieceRepository.existsByidPiece(dtoPiece.idPiece());

        if (existsPiece) {
            return Result.error(new Exception("The current piece already exists!"));
        }

        PieceModel piece = new PieceModel();
        piece.setIdPiece(dtoPiece.idPiece());
        piece.setName(dtoPiece.name());
        piece.setTheoristWeight(dtoPiece.theoristWeight());
        piece.setRealWeight(dtoPiece.realWeight());
        piece.setState(dtoPiece.state());

        pieceRepository.save(piece);

        CreatePieceResponseDto response = new CreatePieceResponseDto(piece.getIdPiece(), "Piece created succesfull!");

        return Result.ok(response);
    }

    public Result<ListAllPieceResponseDto, Exception> listAllPieces() {
        try {
            List<PieceDto> pieceDtos = pieceRepository.findAll().stream().map(piece -> {
                PieceDto pieceDto = new PieceDto(piece.getIdPiece(), piece.getName(), piece.getTheoristWeight(),
                        piece.getRealWeight(), piece.getState());

                return pieceDto;
            }).toList();

            ListAllPieceResponseDto response = new ListAllPieceResponseDto("pieces get succesfull!", pieceDtos);

            return Result.ok(response);
        } catch (Exception e) {
            return Result.error(new Exception("Error to get all pieces!"));
        }
    }

    public Result<ListPieceByIdResponseDto, Exception> listPieceById(Long idPiece) {
        try {
            return pieceRepository.findByidPiece(idPiece).map(piece -> {
                PieceDto pieceDto = new PieceDto(piece.getIdPiece(), piece.getName(), piece.getTheoristWeight(),
                        piece.getRealWeight(), piece.getState());

                ListPieceByIdResponseDto response = new ListPieceByIdResponseDto("piece get succesfull!", pieceDto);

                return Result.ok(response);
            }).orElseGet(() -> Result.error(new Exception("The piece doesnt exists!")));

        } catch (Exception e) {
            return Result.error(new Exception("Error to get current Piece!"));
        }
    }

    public Result<UpdatePieceResponseDto, Exception> updatePiece(Long idPiece, UpdatePieceRequestDto dtoPiece) {
        try {
            return pieceRepository.findByidPiece(idPiece).map(piece -> {

                piece.setName(dtoPiece.name());
                piece.setTheoristWeight(dtoPiece.theoristWeight());
                piece.setRealWeight(dtoPiece.realWeight());
                piece.setState(dtoPiece.state());

                pieceRepository.save(piece);

                UpdatePieceResponseDto response = new UpdatePieceResponseDto(piece.getName(),
                        "Piece update succesfull!");

                return Result.ok(response);

            }).orElseGet(() -> Result.error(new Exception("The current piece doesnt exist!")));

        } catch (Exception e) {
            return Result.error(new Exception("Error to update the current Piece!"));
        }
    }

    public Result<DeletePieceReponseDto, Exception> deletePiece(Long idPiece) {
        try {
            return pieceRepository.findByidPiece(idPiece).map(piece -> {
                pieceRepository.delete(piece);

                DeletePieceReponseDto response = new DeletePieceReponseDto(piece.getName(),
                        "Piece deleted succesfull!");

                return Result.ok(response);

            }).orElseGet(() -> Result.error(new Exception("The current piece doesnt not exists!")));

        } catch (Exception e) {
            return Result.error(new Exception("Error to delete the piece!"));
        }
    }
}
