package com.dev.ms_curso.service.course;

import com.dev.ms_curso.dto.CourseDto;
import com.dev.ms_curso.dto.kafka.Course;
import com.dev.ms_curso.dto.request.CreateCourseRequestDto;
import com.dev.ms_curso.dto.request.UpdateCourseRequestDto;
import com.dev.ms_curso.dto.response.CourseResponseDto;
import com.dev.ms_curso.entity.database.CourseEntity;
import com.dev.ms_curso.exception.CourseNotFoundException;
import com.dev.ms_curso.repository.CourseRepository;
import com.dev.ms_curso.service.kafka.KafkaService;
import com.dev.ms_curso.service.redis.RedisService;
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
    private RedisService redisService;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ModelMapper modelMapper;

    public void saveCourse(CreateCourseRequestDto request) {

        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setName(request.getName());
        courseEntity.setCapacity(request.getCapacity());
        courseEntity.setDescription(request.getDescription());

        CourseEntity createdCourse = repository.saveAndFlush(courseEntity);

        // Redis
        redisService.deleteByKey("courses-list");

        // Kafka Producer
        Course courseKafkaContract = Course.builder()
                .id(createdCourse.getId())
                .name(createdCourse.getName())
                .description(createdCourse.getDescription())
                .capacity(createdCourse.getCapacity())
                .integrated(false)
                .build();

        kafkaService.sendMessage(courseKafkaContract);
    }

    public CourseResponseDto getCourseById(UUID id) {

        Optional<List<CourseDto>> optionalCache = redisService.getCoursesByKey(id.toString());

        if (optionalCache.isPresent()) {
            CourseDto cachedCourse = optionalCache.get().get(0);
            return modelMapper.map(cachedCourse, CourseResponseDto.class);
        }

        CourseEntity courseEntity = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        redisService.saveCourses(id.toString(), List.of(modelMapper.map(courseEntity, CourseDto.class)));

        return modelMapper.map(courseEntity, CourseResponseDto.class);
    }

    public List<CourseResponseDto> getAllCourses() {

        Optional<List<CourseDto>> optionalCache = redisService.getCoursesByKey("courses-list");

        if (optionalCache.isPresent()) {
            List<CourseDto> cachedCourse = optionalCache.get();

            return cachedCourse.stream()
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

        redisService.saveCourses("courses-list", coursesDto);

        return courseEntities.stream().map(entity -> modelMapper.map(entity, CourseResponseDto.class)).toList();
    }

    public CourseResponseDto updateCourse(UUID id, UpdateCourseRequestDto request) {

        CourseEntity courseEntity = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        courseEntity.setName(request.getName() != null ? request.getName() : courseEntity.getName());
        courseEntity.setDescription(request.getDescription() != null ? request.getDescription() : courseEntity.getDescription());
        courseEntity.setCapacity(request.getCapacity() != null ? request.getCapacity() : courseEntity.getCapacity());

        repository.save(courseEntity);

        redisService.deleteByKey(courseEntity.getId().toString());
        redisService.deleteByKey("courses-list");

        return modelMapper.map(courseEntity, CourseResponseDto.class);
    }

    public void deleteCourse(UUID id) {

        CourseEntity courseEntity = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        repository.delete(courseEntity);

        redisService.deleteByKey(courseEntity.getId().toString());
        redisService.deleteByKey("courses-list");
    }
}
