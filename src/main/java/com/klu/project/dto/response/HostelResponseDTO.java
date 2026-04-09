package com.klu.project.dto.response;

public class HostelResponseDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private String roomNumber;
    private String block;
    private java.math.BigDecimal fees;

    public HostelResponseDTO() {}

    public HostelResponseDTO(Long id, Long studentId, String studentName, String roomNumber, String block, java.math.BigDecimal fees) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.roomNumber = roomNumber;
        this.block = block;
        this.fees = fees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
