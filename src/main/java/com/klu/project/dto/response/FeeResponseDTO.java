package com.klu.project.dto.response;

public class FeeResponseDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private String type;
    private java.math.BigDecimal amount;
    private String status;
    private String receiptUrl;
    private java.time.LocalDateTime createdAt;

    public FeeResponseDTO() {}

    public FeeResponseDTO(Long id, Long studentId, String studentName, String type, java.math.BigDecimal amount,
                          String status, String receiptUrl, java.time.LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.receiptUrl = receiptUrl;
        this.createdAt = createdAt;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
