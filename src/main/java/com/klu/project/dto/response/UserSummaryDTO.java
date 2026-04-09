package com.klu.project.dto.response;

public class UserSummaryDTO {
    @com.fasterxml.jackson.annotation.JsonProperty("id")
    private Long id;
    @com.fasterxml.jackson.annotation.JsonProperty("username")
    private String username;
    @com.fasterxml.jackson.annotation.JsonProperty("fullName")
    private String fullName;
    @com.fasterxml.jackson.annotation.JsonProperty("role")
    private String role;

    public UserSummaryDTO() {}

    public UserSummaryDTO(Long id, String username, String fullName, String role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
