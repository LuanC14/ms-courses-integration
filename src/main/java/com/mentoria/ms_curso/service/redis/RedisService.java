package com.mentoria.ms_curso.service.redis;


import com.mentoria.ms_curso.dto.CourseDto;
import com.mentoria.ms_curso.entity.cache.CourseCache;
import com.mentoria.ms_curso.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisRepository redisRepository;

    public void saveCourses(String cacheKey, List<CourseDto> courses) {
        var courseCache = new CourseCache(cacheKey, courses);
        redisRepository.save(courseCache);
    }

    public Optional<List<CourseDto>> getCoursesByKey(String key) {
        Optional<CourseCache> optionalCourseCache = redisRepository.findById(key);

        if (optionalCourseCache.isPresent()) {
            log.info("[REDIS] - Dados encontrados em cache");
            return Optional.of(optionalCourseCache.get().getCourses());
        } else {
            return Optional.empty();
        }
    }

    public void deleteByKey(String key) {
        redisRepository.deleteById(key);
    }

    public void deleteAll() {
        redisRepository.deleteAll();
    }
}
