package com.dev.ms_curso.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class ErrorResponseDto {

    @JsonProperty("status")
    private String status;

    @JsonProperty("detalhes")
    private String details;

    public static ResponseEntity<?> buildResponseEntityErrorReturn(Exception ex, HttpStatus httpStatus) {
        var status = "Ocorreu um erro de status " + httpStatus.value();
        return ResponseEntity.status(httpStatus).body(new ErrorResponseDto(status, ex.getMessage()));
    }
}
