//package com.dev.ms_curso.configuration;
//
//import com.dev.ms_curso.dto.kafka.Course;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//@RequiredArgsConstructor
//public class KafkaProducerConfig {
//
//    @Bean
//    public KafkaTemplate<String, Course> kafkaTemplate(KafkaProperties kafkaProperties) {
//        Map<String, Object> kafkaPropertiesMap = kafkaProperties.buildProducerProperties(null);
//        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaPropertiesMap));
//    }
//}
