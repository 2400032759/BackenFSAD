package com.klu.project.dto.request;

import jakarta.validation.constraints.*;
public class ChatMessageRequestDTO {

    @NotBlank(message = "Group name is required")
    private String groupName;

    @NotBlank(message = "Message is required")
    private String message;

    public ChatMessageRequestDTO() {}

    public ChatMessageRequestDTO(String groupName, String message) {
        this.groupName = groupName;
        this.message = message;
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
}
