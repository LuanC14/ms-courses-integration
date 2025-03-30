package com.dev.ms_curso.service.event;

import com.dev.ms_curso.model.dto.CourseDto;
import com.dev.ms_curso.infrastructure.kafka.KafkaProducer;
import com.dev.ms_curso.infrastructure.kafka.CourseAvro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private KafkaProducer kafkaProducer;

    public void sendEvent(CourseDto courseDto) {
        CourseAvro courseAvro = CourseAvro.newBuilder()
                .setId(courseDto.getId().toString())
                .setName(courseDto.getName())
                .setDescription(courseDto.getDescription())
                .setCapacity(courseDto.getCapacity())
                .setIntegrated(true)
                .build();

        kafkaProducer.sendMessage(courseAvro);
    }
}
