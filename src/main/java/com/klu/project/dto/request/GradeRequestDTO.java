package com.klu.project.dto.request;

import jakarta.validation.constraints.*;
public class GradeRequestDTO {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Marks are required")
    @DecimalMin(value = "0.0", message = "Marks cannot be negative")
    @DecimalMax(value = "100.0", message = "Marks cannot exceed 100")
    private Double marks;

    @NotBlank(message = "Grade is required")
    @Size(max = 2, message = "Grade must be at most 2 characters")
    private String grade;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(PASS|FAIL)$", message = "Status must be PASS or FAIL")
    private String status;

    public GradeRequestDTO() {}

    public GradeRequestDTO(Long studentId, Long courseId, Double marks, String grade, String status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
        this.grade = grade;
        this.status = status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
