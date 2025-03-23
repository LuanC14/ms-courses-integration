package com.dev.ms_curso.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseRequestDto {

    @JsonProperty("nome")
    @Size(min = 3, max = 50, message = "O nome do curso não pode ter menos de 4 ou mais de 50 caracteres")
    private String name;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("capacidade")
    private Integer capacity;
}
