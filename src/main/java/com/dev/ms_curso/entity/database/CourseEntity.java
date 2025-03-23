package com.dev.ms_curso.entity.database;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CURSO")
public class CourseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "descricao")
    private String description;

    @Column(name = "nome")
    private String name;

    @Column(name = "capacidade")
    private Integer capacity;

    private Boolean integrated;

}