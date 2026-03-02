package com.archives.backend.shared.utils.helper;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.archives.backend.shared.utils.result.Result;

public class ResponseEntityHelper {
    static public <T> ResponseEntity<?> toResponseEntity(Result<T, Exception> result) {
        if (result == null) {
            return ResponseEntity.internalServerError().body("Internal error: result is null");
        }

        if (!result.isOk()) {
            return ResponseEntity.badRequest().body(Map.of("message", result.getError().getMessage()));
        }

        return ResponseEntity.ok(result.getValue());
    }
}
