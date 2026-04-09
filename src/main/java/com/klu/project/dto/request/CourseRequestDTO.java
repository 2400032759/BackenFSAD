package com.klu.project.dto.request;

import jakarta.validation.constraints.*;
public class CourseRequestDTO {

    @NotBlank(message = "Course name is required")
    private String name;

    @NotBlank(message = "Course code is required")
    private String code;

    @NotBlank(message = "Section is required")
    private String section;

    @NotBlank(message = "Room is required")
    private String room;

    private Long facultyId;

    public CourseRequestDTO() {}

    public CourseRequestDTO(String name, String code, String section, String room, Long facultyId) {
        this.name = name;
        this.code = code;
        this.section = section;
        this.room = room;
        this.facultyId = facultyId;
    }

    public CourseRequestDTO(String name, String code, String section, String room) {
        this(name, code, section, room, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
}
