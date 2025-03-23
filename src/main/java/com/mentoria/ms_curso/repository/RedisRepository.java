package com.mentoria.ms_curso.repository;

import com.mentoria.ms_curso.entity.cache.CourseCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<CourseCache, String> {
}
