package com.pmu.pmu_project.controlers;

import com.pmu.pmu_project.models.dto.CourseDto;
import com.pmu.pmu_project.models.MyResponse;
import com.pmu.pmu_project.services.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

@Slf4j
@RestController(value = "/api")
public class CourseControler {

    private final CourseService courseService;

    public CourseControler(CourseService courseService) {
        this.courseService = courseService;
    }


    @ApiOperation(value = "Get all entities", notes = "Returns a list of all entities")
    @PostMapping(value = "/courses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCourse(@Valid @RequestBody CourseDto courseRequest){

        return MyResponse.response(
                HttpStatus.CREATED,
                "La course a été Crée",
                courseService.saveCourse(courseRequest)
        );
    }

}
