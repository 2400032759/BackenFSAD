package com.klu.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transports")
public class Transport extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    private User student;

    @Column(nullable = false)
    private String busNumber;

    @Column(nullable = false)
    private String route;

    @Column(nullable = false)
    private String driverContact;

    public Transport() {}

    public Transport(User student, String busNumber, String route, String driverContact) {
        this.student = student;
        this.busNumber = busNumber;
        this.route = route;
        this.driverContact = driverContact;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }
}
