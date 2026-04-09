package com.klu.project.dto.response;

public class ChatMessageResponseDTO {
    private Long id;
    private Long senderId;
    private String senderName;
    private String groupName;
    private String message;
    private java.time.LocalDateTime timestamp;

    public ChatMessageResponseDTO() {}

    public ChatMessageResponseDTO(Long id, Long senderId, String senderName, String groupName, String message, java.time.LocalDateTime timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.groupName = groupName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public java.time.LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.time.LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
