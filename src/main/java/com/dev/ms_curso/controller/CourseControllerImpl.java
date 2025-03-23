package com.dev.ms_curso.controller;

import com.dev.ms_curso.dto.request.CreateCourseRequestDto;
import com.dev.ms_curso.dto.request.UpdateCourseRequestDto;
import com.dev.ms_curso.service.course.CourseService;
import com.dev.ms_curso.dto.response.CourseResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/curso")
public class CourseControllerImpl implements CourseController {

    @Autowired
    private CourseService service;

    @Override
    public ResponseEntity<CourseResponseDto> create(CreateCourseRequestDto request) {
        service.saveCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<CourseResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @Override
    public ResponseEntity<CourseResponseDto> getById(UUID id) {
        CourseResponseDto response = service.getCourseById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CourseResponseDto> update(UUID id, @Valid UpdateCourseRequestDto request) {
        return ResponseEntity.ok(service.updateCourse(id, request));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(UUID id) {
        service.deleteCourse(id);
    }
}
