package com.pmu.pmu_project.repositories;

import com.pmu.pmu_project.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByDateAndNumero(Date date, Integer numero);
}
