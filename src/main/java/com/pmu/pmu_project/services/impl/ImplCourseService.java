package com.pmu.pmu_project.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmu.pmu_project.exceptions.InvalidDataRequestException;
import com.pmu.pmu_project.models.Course;
import com.pmu.pmu_project.models.Partant;
import com.pmu.pmu_project.models.dto.CourseDto;
import com.pmu.pmu_project.repositories.CourseRepository;
import com.pmu.pmu_project.services.CourseService;
import com.pmu.pmu_project.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.pmu.pmu_project.utils.Utils.checkIfNumbersOfPartantsIsValid;
import static com.pmu.pmu_project.utils.Utils.convertStringToDate;


@Service
@Slf4j
public class ImplCourseService implements CourseService {

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ImplCourseService(CourseRepository courseRepository, ObjectMapper objectMapper,
                             KafkaTemplate<String, String> kafkaTemplate) {
        this.courseRepository = courseRepository;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Course saveCourse(CourseDto courseRequest) {
        if (!checkIfNumbersOfPartantsIsValid(courseRequest.getPartants())) {
            throw new InvalidDataRequestException(
                    String.format("Duplicate or missing partant numbers in course: %s", courseRequest));
        }

        Course c = new Course();
        c.setDate(convertStringToDate(courseRequest.getDate()));
        c.setNom(courseRequest.getNom());
        c.setNumero(courseRequest.getNumero());

        if (courseRepository.existsByDateAndNumero(c.getDate(), c.getNumero())) {
            throw new InvalidDataRequestException(Constants.NUMERO_NOM_COURSE_EXISTANTE);
        }

        List<Partant> listPartants = courseRequest.getPartants().stream().map(p ->
                new Partant(p.getNumero(), p.getNom(), c)
        ).toList();

        c.setPartants(listPartants);

        Course course = courseRepository.save(c);

        try {
            kafkaTemplate.sendDefault(objectMapper.writeValueAsString(course));
            log.info("KAFKA");
            log.info("COURSE: {} ", objectMapper.writeValueAsString(course));
        } catch (Exception e) {
            log.error("An error occurred while sending the course to Kafka", e);
        }

        return course;
    }
}
