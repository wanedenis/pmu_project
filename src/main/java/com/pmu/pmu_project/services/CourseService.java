package com.pmu.pmu_project.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pmu.pmu_project.models.Course;
import com.pmu.pmu_project.models.dto.CourseDto;

public interface CourseService {

    public Course saveCourse(CourseDto courseRequest);

}
