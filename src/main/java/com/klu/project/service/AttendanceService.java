package com.klu.project.service;

import com.klu.project.dto.request.AttendanceRequestDTO;
import com.klu.project.dto.response.AttendanceResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendanceService {

    List<AttendanceResponseDTO> markAttendance(AttendanceRequestDTO request, String username);

    Page<AttendanceResponseDTO> getStudentAttendance(String username, Long courseId, Pageable pageable);
}
