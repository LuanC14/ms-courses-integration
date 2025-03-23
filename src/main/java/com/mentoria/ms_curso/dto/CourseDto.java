package com.mentoria.ms_curso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private UUID id;

    private String description;

    private String name;

    private Integer capacity;

    private boolean integrated;
}
