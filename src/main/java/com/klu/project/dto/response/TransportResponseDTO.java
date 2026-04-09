package com.klu.project.dto.response;

public class TransportResponseDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private String busNumber;
    private String route;
    private String driverContact;

    public TransportResponseDTO() {}

    public TransportResponseDTO(Long id, Long studentId, String studentName, String busNumber, String route, String driverContact) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.busNumber = busNumber;
        this.route = route;
        this.driverContact = driverContact;
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
