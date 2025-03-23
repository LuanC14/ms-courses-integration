package com.dev.ms_curso.service.kafka;

import com.dev.ms_curso.dto.kafka.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private static final String TP_CREATED_COURSES = "TP-CREATED-COURSES";
    private static final String TP_INTEGRATED_COURSES = "TP-INTEGRATED-COURSES";
    @Autowired
    private KafkaTemplate<String, Course> kafkaTemplate;

    public void sendMessage(Course course) {
        kafkaTemplate.send(TP_CREATED_COURSES, course);
    }

}
