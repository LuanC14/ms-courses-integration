package com.mentoria.ms_curso.repository;

import com.mentoria.ms_curso.entity.database.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
}
