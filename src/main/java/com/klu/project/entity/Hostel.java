package com.klu.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "hostels")
public class Hostel extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    private User student;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String block;

    @Column(nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal fees;

    public Hostel() {}

    public Hostel(User student, String roomNumber, String block, java.math.BigDecimal fees) {
        this.student = student;
        this.roomNumber = roomNumber;
        this.block = block;
        this.fees = fees;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public java.math.BigDecimal getFees() {
        return fees;
    }

    public void setFees(java.math.BigDecimal fees) {
        this.fees = fees;
    }
}
