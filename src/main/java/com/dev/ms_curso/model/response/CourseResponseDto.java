package com.dev.ms_curso.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("capacidade")
    private Integer capacity;

    @JsonProperty("integrado")
    private Boolean integrated;
}
