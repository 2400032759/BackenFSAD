package com.klu.project.dto.response;

public class CourseResponseDTO {
    private Long id;
    private String name;
    private String code;
    private Long facultyId;
    private String facultyName;
    private String section;
    private String room;
    private java.time.LocalDateTime createdAt;

    public CourseResponseDTO() {}

    public CourseResponseDTO(Long id, String name, String code, Long facultyId, String facultyName, String section, String room, java.time.LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.section = section;
        this.room = room;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
