package com.klu.project.dto.request;

import jakarta.validation.constraints.*;
public class ComplaintRequestDTO {

    @NotBlank(message = "Complaint message is required")
    @Size(min = 10, max = 2000, message = "Message must be between 10 and 2000 characters")
    private String message;

    public ComplaintRequestDTO() {}

    public ComplaintRequestDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
