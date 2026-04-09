package com.klu.project.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public class AttendanceRequestDTO {

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Date is required")
    private java.time.LocalDate date;

    @NotEmpty(message = "Attendance records cannot be empty")
    @Valid
    private java.util.List<AttendanceRecordDTO> records;

    public AttendanceRequestDTO() {}

    public AttendanceRequestDTO(Long courseId, java.time.LocalDate date, java.util.List<AttendanceRecordDTO> records) {
        this.courseId = courseId;
        this.date = date;
        this.records = records;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public java.time.LocalDate getDate() {
        return date;
    }

    public void setDate(java.time.LocalDate date) {
        this.date = date;
    }

    public java.util.List<AttendanceRecordDTO> getRecords() {
        return records;
    }

    public void setRecords(java.util.List<AttendanceRecordDTO> records) {
        this.records = records;
    }

    public static class AttendanceRecordDTO {

        @NotNull(message = "Student ID is required")
        private Long studentId;

        @NotBlank(message = "Status is required")
        @Pattern(regexp = "^(PRESENT|ABSENT)$", message = "Status must be PRESENT or ABSENT")
        private String status;

        public AttendanceRecordDTO() {}

        public AttendanceRecordDTO(Long studentId, String status) {
            this.studentId = studentId;
            this.status = status;
        }

        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
