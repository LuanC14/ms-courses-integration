package com.dev.ms_curso.infrastructure.redis;


import com.dev.ms_curso.model.dto.CourseDto;
import com.dev.ms_curso.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RedisCourseManager {

    @Autowired
    private RedisRepository redisRepository;

    public void save(String cacheKey, List<CourseDto> courses) {
        var courseCache = new CourseCache(cacheKey, courses);
        redisRepository.save(courseCache);
    }

    public List<CourseDto> get(String key) {
        Optional<CourseCache> optionalCourseCache = redisRepository.findById(key);

        if (optionalCourseCache.isPresent()) {
            log.info("[REDIS] - Dados encontrados em cache");
            return optionalCourseCache.get().getCourses();
        } else {
            return Collections.emptyList();
        }
    }

    public void delete(String key) {
        redisRepository.deleteById(key);
    }

}
