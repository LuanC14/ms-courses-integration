package com.dev.ms_curso.dto.kafka;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Course {

    private String id;

    private String description;

    private String name;

    private Integer capacity;

    private Boolean integrated;
}
