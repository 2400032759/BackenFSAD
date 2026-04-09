package com.klu.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_courses", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code", "section"})
})
public class Course extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private User faculty;

    @Column(nullable = false)
    private String section;

    @Column(nullable = false)
    private String room;

    public Course() {}

    public Course(String name, String code, User faculty, String section, String room) {
        this.name = name;
        this.code = code;
        this.faculty = faculty;
        this.section = section;
        this.room = room;
    }

    public Course(String name, String code, User faculty) {
        this(name, code, faculty, "N/A", "N/A");
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

    public User getFaculty() {
        return faculty;
    }

    public void setFaculty(User faculty) {
        this.faculty = faculty;
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
}
