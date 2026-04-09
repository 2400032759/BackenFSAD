package com.klu.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_grades", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "course_id"})
})
public class Grade extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private Double marks;

    @Column(nullable = false, length = 2)
    private String grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeStatus status;

    public Grade() {}

    public Grade(User student, Course course, Double marks, String grade, GradeStatus status) {
        this.student = student;
        this.course = course;
        this.marks = marks;
        this.grade = grade;
        this.status = status;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public GradeStatus getStatus() {
        return status;
    }

    public void setStatus(GradeStatus status) {
        this.status = status;
    }
}
