package com.dev.ms_curso.service.cache;

import com.dev.ms_curso.model.dto.CourseDto;
import com.dev.ms_curso.infrastructure.redis.RedisCourseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseCacheService {

    @Autowired
    private RedisCourseManager redisCourseManager;

    private static final String COURSE_LIST_KEY = "courses-list";

    public void saveCourse(String id, CourseDto courseDto) {
        redisCourseManager.save(id, List.of(courseDto));
    }


    public void saveCourseList(List<CourseDto> courseDtos) {
        redisCourseManager.save(COURSE_LIST_KEY, courseDtos);
    }

    public Optional<CourseDto> getCourseById(UUID id) {
        List<CourseDto> optionalCourseDtoList = redisCourseManager.get(id.toString());
        return optionalCourseDtoList.isEmpty() ? Optional.empty() : Optional.of(optionalCourseDtoList.get(0));
    }

    public List<CourseDto> getCoursesList() {
        return redisCourseManager.get(COURSE_LIST_KEY);
    }

    public void deleteCoursesList() {
        redisCourseManager.delete(COURSE_LIST_KEY);
    }

    public void deleteCourseById(UUID id) {
        redisCourseManager.delete(id.toString());
    }

}
