package com.archives.backend.features.blocks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archives.backend.features.blocks.dtos.BlockDto;
import com.archives.backend.features.blocks.dtos.request.RegisterBlockRequestDto;
import com.archives.backend.features.blocks.dtos.request.UpdateBlockRequestDto;
import com.archives.backend.features.blocks.dtos.response.DeleteBlockResponse;
import com.archives.backend.features.blocks.dtos.response.ListAllBlockResponse;
import com.archives.backend.features.blocks.dtos.response.ListBlockByIdResponse;
import com.archives.backend.features.blocks.dtos.response.RegisterBlockResponseDto;
import com.archives.backend.features.blocks.dtos.response.UpdateBlockResponseDto;
import com.archives.backend.features.blocks.models.BlocksModel;
import com.archives.backend.features.blocks.repository.IBlockRepository;
import com.archives.backend.shared.utils.result.Result;

@Service
public class BlockServices {

    @Autowired
    private IBlockRepository blockRepository;

    public Result<RegisterBlockResponseDto, Exception> registerBlock(RegisterBlockRequestDto dtoBlock) {

        boolean existBlock = blockRepository.existsByblockCode(dtoBlock.blockCode());

        if (existBlock) {
            return Result.error(new Exception("the block already exits!"));
        }

        BlocksModel block = new BlocksModel();
        block.setBlockCode(dtoBlock.blockCode());
        block.setName(dtoBlock.name());

        blockRepository.save(block);

        RegisterBlockResponseDto response = new RegisterBlockResponseDto(block.getBlockCode(),
                "Block register succesfull!");

        return Result.ok(response);
    }

    public Result<ListAllBlockResponse, Exception> listAllBlocks() {

        try {
            List<BlockDto> listBlocks = blockRepository.findAll().stream()
                    .map(block -> new BlockDto(block.getBlockCode(), block.getName()))
                    .toList();

            ListAllBlockResponse response = new ListAllBlockResponse("Blocks get succesfull!", listBlocks);

            return Result.ok(response);

        } catch (Exception e) {
            return Result.error(new Exception("Error to get all blocks!"));
        }
    }

    public Result<ListBlockByIdResponse, Exception> listBlockById(String block_code) {
        try {
            return blockRepository.findByBlockCode(block_code).map(block -> {
                BlockDto blockDto = new BlockDto(block.getBlockCode(), block.getName());

                ListBlockByIdResponse response = new ListBlockByIdResponse("Block get succesfull!", blockDto);

                return Result.ok(response);
            })
                    .orElseGet(() -> Result.error(new Exception("The current block Not Exists!")));

        } catch (Exception e) {
            return Result.error(new Exception("Error to get current block!"));
        }
    }

    public Result<UpdateBlockResponseDto, Exception> updateBlock(String block_code, UpdateBlockRequestDto blockDto) {
        try {
            return blockRepository.findByBlockCode(block_code).map(block -> {
                block.setName(blockDto.name());

                blockRepository.save(block);

                UpdateBlockResponseDto response = new UpdateBlockResponseDto(block.getBlockCode(),
                        "Block update succesfull!");

                return Result.ok(response);

            }).orElseGet(() -> Result.error(new Exception("the current block doesnt not exists!")));

        } catch (Exception e) {
            return Result.error(new Exception("Error to update the current block!"));
        }
    }

    public Result<DeleteBlockResponse, Exception> deleteBlock(String block_code) {
        try {
            return blockRepository.findByBlockCode(block_code).map(block -> {
                blockRepository.delete(block);
                DeleteBlockResponse response = new DeleteBlockResponse(block.getBlockCode(),
                        "Block delete succesfull!");

                return Result.ok(response);

            }).orElseGet(() -> Result.error(new Exception("The current block doesnt exists!")));
        } catch (Exception e) {
            return Result.error(new Exception("Error to delete the current block!"));
        }
    }
}
