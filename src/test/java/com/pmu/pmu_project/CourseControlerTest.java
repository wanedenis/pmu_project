package com.pmu.pmu_project;

import com.pmu.pmu_project.controlers.CourseControler;

import com.pmu.pmu_project.models.Course;
import com.pmu.pmu_project.models.dto.CourseDto;
import com.pmu.pmu_project.models.dto.PartantDto;
import com.pmu.pmu_project.services.impl.ImplCourseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    // Should return a 201 status code and a success message when a valid course is created
    @Test
    public void test_valid_course_created() {
        // Given
        CourseDto courseDto = new CourseDto();
        courseDto.setDate("2022-01-01");
        courseDto.setNumero(1);
        courseDto.setNom("Course 1");
        List<PartantDto> partants = new ArrayList<>();
        partants.add(new PartantDto(1, "Partant 1"));
        partants.add(new PartantDto(2, "Partant 2"));
        partants.add(new PartantDto(3, "Partant 3"));
        courseDto.setPartants(partants);

        ImplCourseService mockCourseService = mock(ImplCourseService.class);
        Course expectedCourse = new Course();
        when(mockCourseService.saveCourse(courseDto)).thenReturn(expectedCourse);

        CourseControler courseControler = new CourseControler(mockCourseService);

        // When
        ResponseEntity<Object> response = courseControler.createCourse(courseDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).toString().contains("La course a été Crée"));
        verify(mockCourseService).saveCourse(courseDto);
    }

    // Should return a 400 status code and an error message when the date in the CourseDto object is null
    @Test
    public void test_null_date_in_course_dto() {
        // Given
        CourseDto courseDto = new CourseDto();
        courseDto.setDate(null);
        courseDto.setNumero(1);
        courseDto.setNom("Course 1");
        List<PartantDto> partants = new ArrayList<>();
        partants.add(new PartantDto(1, "Partant 1"));
        partants.add(new PartantDto(2, "Partant 2"));
        partants.add(new PartantDto(3, "Partant 3"));
        courseDto.setPartants(partants);

        ImplCourseService mockCourseService = mock(ImplCourseService.class);
        CourseControler courseControler = new CourseControler(mockCourseService);

        // When
        ResponseEntity<Object> response = courseControler.createCourse(courseDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).toString()
                .contains("Date Invalide: La date est NULLE"));
        verifyNoInteractions(mockCourseService);
    }

    // Should return a 400 status code and an error message when the date in the CourseDto object is in an invalid format
    @Test
    public void test_invalid_date_format_in_course_dto() {
        // Given
        CourseDto courseDto = new CourseDto();
        courseDto.setDate("2022-01");
        courseDto.setNumero(1);
        courseDto.setNom("Course 1");
        List<PartantDto> partants = new ArrayList<>();
        partants.add(new PartantDto(1, "Partant 1"));
        partants.add(new PartantDto(2, "Partant 2"));
        partants.add(new PartantDto(3, "Partant 3"));
        courseDto.setPartants(partants);

        ImplCourseService mockCourseService = mock(ImplCourseService.class);
        CourseControler courseControler = new CourseControler(mockCourseService);

        // When
        ResponseEntity<Object> response = courseControler.createCourse(courseDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).toString()
                .contains("Date invalide: Le format de la date est invalide"));
        verifyNoInteractions(mockCourseService);
    }



}