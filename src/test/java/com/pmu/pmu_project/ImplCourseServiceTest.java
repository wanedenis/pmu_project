package com.pmu.pmu_project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmu.pmu_project.exceptions.InvalidDataRequestException;
import com.pmu.pmu_project.models.Course;
import com.pmu.pmu_project.models.dto.CourseDto;
import com.pmu.pmu_project.models.dto.PartantDto;
import com.pmu.pmu_project.repositories.CourseRepository;
import com.pmu.pmu_project.services.impl.ImplCourseService;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ImplCourseServiceTest {


    // Save a valid course successfully
    @Test
    public void test_save_valid_course_successfully() {
        // Mock dependencies
        CourseRepository mockCourseRepository = mock(CourseRepository.class);
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        KafkaTemplate<String, String> mockKafkaTemplate = mock(KafkaTemplate.class);

        CourseDto courseDto = getCourseDto(2);

        // Create expected result
        Course expectedCourse = new Course();

        // Set up mock behavior
        when(mockCourseRepository.save(any(Course.class))).thenReturn(expectedCourse);

        // Create instance of class under test
        ImplCourseService implCourseService = new ImplCourseService(mockCourseRepository, mockObjectMapper, mockKafkaTemplate);

        // Invoke method under test
        Course result = implCourseService.saveCourse(courseDto);

        // Perform assertions
        assertEquals(expectedCourse, result);
        verify(mockCourseRepository).save(any(Course.class));
    }

    private static CourseDto getCourseDto(int numero) {
        // Create test data
        CourseDto courseDto = new CourseDto();
        courseDto.setDate("2022-01-01");
        courseDto.setNumero(1);
        courseDto.setNom("Course 1");
        List<PartantDto> partants = new ArrayList<>();
        partants.add(new PartantDto(1, "Partant 1"));
        partants.add(new PartantDto(numero, "Partant 2"));
        partants.add(new PartantDto(3, "Partant 3"));
        courseDto.setPartants(partants);
        return courseDto;
    }

    // Throw InvalidDataRequestException when saving a course with duplicate partant numbers
    @Test
    public void test_throw_exception_duplicate_partant_numbers() {
        // Mock dependencies
        CourseRepository mockCourseRepository = mock(CourseRepository.class);
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        KafkaTemplate<String, String> mockKafkaTemplate = mock(KafkaTemplate.class);

        // Create test data
        CourseDto courseDto = getCourseDto(1);

        // Create instance of class under test
        ImplCourseService implCourseService = new ImplCourseService(mockCourseRepository, mockObjectMapper, mockKafkaTemplate);

        // Perform assertions
        assertThrows(InvalidDataRequestException.class, () -> implCourseService.saveCourse(courseDto));
    }

}