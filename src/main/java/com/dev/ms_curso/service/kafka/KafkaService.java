package com.dev.ms_curso.service.kafka;

import com.dev.ms_curso.kafka.contracts.CourseAvro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private static final String TP_CREATED_COURSES = "TP-CREATED-COURSES-AVRO";
    private static final String TP_INTEGRATED_COURSES = "TP-INTEGRATED-COURSES";
    @Autowired
    private KafkaTemplate<String, CourseAvro> kafkaTemplate;

    public void sendMessage(CourseAvro course) {
        kafkaTemplate.send(TP_CREATED_COURSES, course);
    }
}
