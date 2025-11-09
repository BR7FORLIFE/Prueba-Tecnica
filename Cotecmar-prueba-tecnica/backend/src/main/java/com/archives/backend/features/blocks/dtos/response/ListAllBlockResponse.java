package com.archives.backend.features.blocks.dtos.response;

import java.util.List;

import com.archives.backend.features.blocks.dtos.BlockDto;

public record ListAllBlockResponse(String message, List<BlockDto> blocks) {

}
