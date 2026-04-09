package com.klu.project.repository;

import com.klu.project.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCode(String code);

    boolean existsByCode(String code);
    boolean existsByCodeAndSection(String code, String section);

    List<Course> findByFacultyId(Long facultyId);

    Page<Course> findByFacultyId(Long facultyId, Pageable pageable);

    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
