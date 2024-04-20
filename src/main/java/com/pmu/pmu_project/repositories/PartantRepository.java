package com.pmu.pmu_project.repositories;

import com.pmu.pmu_project.models.Course;
import com.pmu.pmu_project.models.Partant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartantRepository extends JpaRepository<Partant, Long> {
}
