package com.klu.project.controller;

import com.klu.project.dto.request.CourseRequestDTO;
import com.klu.project.dto.request.EnrollmentRequestDTO;
import com.klu.project.dto.response.ApiResponse;
import com.klu.project.dto.response.CourseResponseDTO;
import com.klu.project.dto.response.EnrollmentResponseDTO;
import com.klu.project.service.CourseService;
import com.klu.project.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.klu.project.dto.response.UserSummaryDTO;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Course management and enrollment APIs")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @Operation(summary = "Create a new course (Faculty only)")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> createCourse(
            @Valid @RequestBody CourseRequestDTO request,
            Authentication authentication) {
        CourseResponseDTO response = courseService.createCourse(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Course created successfully", response));
    }

    @GetMapping
    @Operation(summary = "Get all courses with pagination")
    public ResponseEntity<ApiResponse<Page<CourseResponseDTO>>> getAllCourses(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CourseResponseDTO> courses = courseService.getAllCourses(pageable);
        return ResponseEntity.ok(ApiResponse.success("Courses retrieved successfully", courses));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> getCourseById(@PathVariable Long id) {
        CourseResponseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(ApiResponse.success("Course retrieved successfully", course));
    }

    @PostMapping("/enroll")
    @Operation(summary = "Enroll student in a course (Student only)")
    public ResponseEntity<ApiResponse<EnrollmentResponseDTO>> enroll(
            @Valid @RequestBody EnrollmentRequestDTO request,
            Authentication authentication) {
        EnrollmentResponseDTO response = courseService.enrollStudent(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Enrolled successfully", response));
    }

    @DeleteMapping("/unenroll/{courseId}")
    @Operation(summary = "Unenroll student from a course (Student only)")
    public ResponseEntity<ApiResponse<Void>> unenroll(
            @PathVariable Long courseId,
            Authentication authentication) {
        courseService.unenrollStudent(courseId, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Unenrolled successfully"));
    }

    @GetMapping("/enrollments")
    @Operation(summary = "Get student's enrolled courses")
    public ResponseEntity<ApiResponse<List<EnrollmentResponseDTO>>> getEnrollments(
            Authentication authentication) {
        List<EnrollmentResponseDTO> enrollments = courseService.getStudentEnrollments(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Enrollments retrieved successfully", enrollments));
    }

    @PostMapping("/assign-student")
    @Operation(summary = "Admin: Assign student to a course")
    public ResponseEntity<ApiResponse<EnrollmentResponseDTO>> assignStudent(
            @RequestParam Long studentId, @RequestParam Long courseId) {
        EnrollmentResponseDTO response = courseService.enrollStudentByAdmin(studentId, courseId);
        return ResponseEntity.ok(ApiResponse.success("Student assigned successfully", response));
    }

    @DeleteMapping("/remove-enrollment")
    @Operation(summary = "Admin: Remove student from a course")
    public ResponseEntity<ApiResponse<Void>> removeEnrollment(
            @RequestParam Long studentId, @RequestParam Long courseId) {
        courseService.removeEnrollmentByAdmin(studentId, courseId);
        return ResponseEntity.ok(ApiResponse.success("Enrollment removed successfully"));
    }

    @GetMapping("/users-by-role")
    @Operation(summary = "Admin: Get users by role")
    public ResponseEntity<ApiResponse<List<UserSummaryDTO>>> getUsersByRole(@RequestParam String role) {
        List<UserSummaryDTO> users = courseService.getUsersByRole(role);
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }
    @GetMapping("/{id}/students")
    @Operation(summary = "Get students enrolled in a course")
    public ResponseEntity<ApiResponse<List<UserSummaryDTO>>> getEnrolledStudents(@PathVariable Long id) {
        List<UserSummaryDTO> students = courseService.getEnrolledStudents(id);
        return ResponseEntity.ok(ApiResponse.success("Students retrieved successfully", students));
    }
}
