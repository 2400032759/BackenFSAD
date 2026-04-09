package com.klu.project.dto.response;

public class CourseMaterialResponseDTO {
    private Long id;
    private Long courseId;
    private String courseName;
    private String fileName;
    private String fileUrl;
    private Long uploadedById;
    private String uploadedByName;
    private java.time.LocalDateTime createdAt;

    public CourseMaterialResponseDTO() {}

    public CourseMaterialResponseDTO(Long id, Long courseId, String courseName, String fileName, String fileUrl,
                                     Long uploadedById, String uploadedByName, java.time.LocalDateTime createdAt) {
        this.id = id;
        this.courseId = courseId;
        this.courseName = courseName;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.uploadedById = uploadedById;
        this.uploadedByName = uploadedByName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getUploadedById() {
        return uploadedById;
    }

    public void setUploadedById(Long uploadedById) {
        this.uploadedById = uploadedById;
    }

    public String getUploadedByName() {
        return uploadedByName;
    }

    public void setUploadedByName(String uploadedByName) {
        this.uploadedByName = uploadedByName;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
