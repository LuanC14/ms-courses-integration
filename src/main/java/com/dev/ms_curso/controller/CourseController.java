package com.dev.ms_curso.controller;

import com.dev.ms_curso.model.request.CreateCourseRequestDto;
import com.dev.ms_curso.model.request.UpdateCourseRequestDto;
import com.dev.ms_curso.model.response.ErrorResponseDto;
import com.dev.ms_curso.model.response.CourseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Endpoints de curso", description = "Operações relacionadas a curso")
public interface CourseController {

    @Operation(summary = "Criar um novo curso", description = "Este endpoint cria um novo curso no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Erro no servidor",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @PostMapping
    ResponseEntity<CourseResponseDto> create(@Valid @RequestBody CreateCourseRequestDto request) throws Exception;

    @GetMapping
    @Operation(summary = "Obter cursos existentes", description = "Este endpoint pode retornar todos os cursos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(contentMediaType = "application/json", implementation = CourseResponseDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Erro no servidor",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    ResponseEntity<List<CourseResponseDto>> getAll();

    @GetMapping("/{id}")
    @Operation(summary = "Obter um curso por ID", description = "Este endpoint pode retornar um curso baseado no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Erro no servidor",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    ResponseEntity<CourseResponseDto> getById(@PathVariable UUID id);

    @PutMapping("/{id}")
    @Operation(summary = "Alterar dados do curso pelo ID", description = "Este endpoint pode alterar atributos do curso baseado no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Erro no servidor",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    ResponseEntity<CourseResponseDto> update(@PathVariable("id") UUID id, @RequestBody UpdateCourseRequestDto request);

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar curso pelo ID", description = "Este endpoint pode deletar o curso baseado no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleção realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Erro no servidor",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    void delete(@PathVariable("id") UUID id);
}
