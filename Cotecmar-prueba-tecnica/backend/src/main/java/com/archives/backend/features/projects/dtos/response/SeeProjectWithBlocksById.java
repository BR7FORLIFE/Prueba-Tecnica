package com.archives.backend.features.projects.dtos.response;

import java.util.List;

import com.archives.backend.features.blocks.dtos.BlockDto;

public record SeeProjectWithBlocksById(List<BlockDto> blocks, String message) {

}
