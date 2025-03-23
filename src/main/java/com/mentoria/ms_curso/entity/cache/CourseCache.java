package com.mentoria.ms_curso.entity.cache;

import com.mentoria.ms_curso.dto.CourseDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Data
@RedisHash(value = "course-redis")
public class CourseCache {

    @Id
    private String key;

    private List<CourseDto> courses;

    public CourseCache() {
        this.courses = new ArrayList<>();
    }

    public CourseCache(String key, List<CourseDto> courses) {
        this.key = key;
        this.courses = courses;
    }
}
