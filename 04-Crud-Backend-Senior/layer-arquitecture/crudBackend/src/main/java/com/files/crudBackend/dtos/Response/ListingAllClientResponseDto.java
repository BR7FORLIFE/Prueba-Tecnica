package com.files.crudBackend.dtos.Response;

import java.util.List;

import com.files.crudBackend.model.ClientModel;

public record ListingAllClientResponseDto(
        List<ClientModel> clients,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last) {

}
