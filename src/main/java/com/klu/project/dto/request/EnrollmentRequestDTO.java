package com.klu.project.dto.request;

import jakarta.validation.constraints.*;
public class EnrollmentRequestDTO {

    @NotNull(message = "Course ID is required")
    private Long courseId;

    public EnrollmentRequestDTO() {}

    public EnrollmentRequestDTO(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
