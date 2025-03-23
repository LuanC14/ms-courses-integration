package com.dev.ms_curso.repository;

import com.dev.ms_curso.entity.database.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
}
