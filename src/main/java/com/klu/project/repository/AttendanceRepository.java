package com.klu.project.repository;

import com.klu.project.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);

    Page<Attendance> findByStudentId(Long studentId, Pageable pageable);

    List<Attendance> findByStudentIdAndCourseId(Long studentId, Long courseId);

    Page<Attendance> findByStudentIdAndCourseId(Long studentId, Long courseId, Pageable pageable);

    List<Attendance> findByCourseIdAndDate(Long courseId, LocalDate date);

    boolean existsByStudentIdAndCourseIdAndDate(Long studentId, Long courseId, LocalDate date);
}
