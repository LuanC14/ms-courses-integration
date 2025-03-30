package com.dev.ms_curso.service.course;

import com.dev.ms_curso.model.dto.CourseDto;
import com.dev.ms_curso.model.request.CreateCourseRequestDto;
import com.dev.ms_curso.model.request.UpdateCourseRequestDto;
import com.dev.ms_curso.model.response.CourseResponseDto;
import com.dev.ms_curso.model.entity.CourseEntity;
import com.dev.ms_curso.exception.CourseNotFoundException;
import com.dev.ms_curso.repository.CourseRepository;
import com.dev.ms_curso.service.cache.CourseCacheService;
import com.dev.ms_curso.service.event.EventService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    CourseCacheService courseCacheService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper modelMapper;

    public void saveCourse(CreateCourseRequestDto request) {

        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setName(request.getName());
        courseEntity.setCapacity(request.getCapacity());
        courseEntity.setDescription(request.getDescription());

        CourseEntity createdCourse = repository.saveAndFlush(courseEntity);

        // Cache
        courseCacheService.deleteCoursesList();

        // Event
        eventService.sendEvent(modelMapper.map(createdCourse, CourseDto.class));
    }

    public CourseResponseDto getCourseById(UUID id) {

        Optional<CourseDto> optionalCache = courseCacheService.getCourseById(id);

        if (optionalCache.isPresent()) {
            CourseDto cachedCourse = optionalCache.get();
            return modelMapper.map(cachedCourse, CourseResponseDto.class);
        }

        CourseEntity courseEntity = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        courseCacheService.saveCourse(id.toString(), modelMapper.map(courseEntity, CourseDto.class));

        return modelMapper.map(courseEntity, CourseResponseDto.class);
    }

    public List<CourseResponseDto> getAllCourses() {

        List<CourseDto> cacheCourseList = courseCacheService.getCoursesList();

        if (!cacheCourseList.isEmpty()) {
            return cacheCourseList.stream()
                    .map(course -> modelMapper.map(course, CourseResponseDto.class))
                    .toList();
        }

        List<CourseEntity> courseEntities = repository.findAll();

        if (courseEntities.isEmpty()) {
            throw new CourseNotFoundException("Nenhum curso encontrado");
        }

        List<CourseDto> coursesDto = courseEntities.stream()
                .map(entity -> modelMapper.map(entity, CourseDto.class))
                .toList();

        courseCacheService.saveCourseList(coursesDto);

        return courseEntities.stream().map(entity -> modelMapper.map(entity, CourseResponseDto.class)).toList();
    }

    public CourseResponseDto updateCourse(UUID id, UpdateCourseRequestDto request) {

        CourseEntity courseEntity = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        courseEntity.setName(request.getName() != null ? request.getName() : courseEntity.getName());
        courseEntity.setDescription(request.getDescription() != null ? request.getDescription() : courseEntity.getDescription());
        courseEntity.setCapacity(request.getCapacity() != null ? request.getCapacity() : courseEntity.getCapacity());

        repository.save(courseEntity);

        courseCacheService.deleteCourseById(courseEntity.getId());
        courseCacheService.deleteCoursesList();

        return modelMapper.map(courseEntity, CourseResponseDto.class);
    }

    public void deleteCourse(UUID id) {

        CourseEntity courseEntity = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        repository.delete(courseEntity);

        courseCacheService.deleteCourseById(courseEntity.getId());
        courseCacheService.deleteCoursesList();
    }
}
