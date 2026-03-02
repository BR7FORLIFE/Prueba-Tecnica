package com.archives.backend.features.blocks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archives.backend.features.blocks.dtos.request.RegisterBlockRequestDto;
import com.archives.backend.features.blocks.dtos.request.UpdateBlockRequestDto;
import com.archives.backend.features.blocks.services.BlockServices;
import com.archives.backend.shared.utils.helper.ResponseEntityHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/block")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class BlockController {

    @Autowired
    private BlockServices blockServices;

    @PostMapping
    public ResponseEntity<?> registerBlock(
            @RequestBody @Valid RegisterBlockRequestDto dtoBlock) {
        var result = blockServices.registerBlock(dtoBlock);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @GetMapping
    public ResponseEntity<?> listAllBlocks() {
        var result = blockServices.listAllBlocks();
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @GetMapping("/{block_code}")
    public ResponseEntity<?> listBlockById(@PathVariable String block_code) {
        var result = blockServices.listBlockById(block_code);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @PutMapping("/{block_code}")
    public ResponseEntity<?> updateBlock(@PathVariable String block_code, @RequestBody @Valid UpdateBlockRequestDto blockDto) {
        var result = blockServices.updateBlock(block_code, blockDto);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @DeleteMapping("/{block_code}")
    public ResponseEntity<?> deleteBlock(@PathVariable String block_code) {
        var result = blockServices.deleteBlock(block_code);
        return ResponseEntityHelper.toResponseEntity(result);
    }
}