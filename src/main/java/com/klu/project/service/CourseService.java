package com.klu.project.service;

import com.klu.project.dto.request.CourseRequestDTO;
import com.klu.project.dto.request.EnrollmentRequestDTO;
import com.klu.project.dto.response.CourseResponseDTO;
import com.klu.project.dto.response.EnrollmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    CourseResponseDTO createCourse(CourseRequestDTO request, String username);

    Page<CourseResponseDTO> getAllCourses(Pageable pageable);

    CourseResponseDTO getCourseById(Long id);

    EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO request, String username);

    void unenrollStudent(Long courseId, String username);

    List<EnrollmentResponseDTO> getStudentEnrollments(String username);

    EnrollmentResponseDTO enrollStudentByAdmin(Long studentId, Long courseId);
    
    void removeEnrollmentByAdmin(Long studentId, Long courseId);

    List<com.klu.project.dto.response.UserSummaryDTO> getUsersByRole(String roleName);

    List<com.klu.project.dto.response.UserSummaryDTO> getEnrolledStudents(Long courseId);
}
