package com.klu.project.controller;

import com.klu.project.dto.request.AttendanceRequestDTO;
import com.klu.project.dto.response.ApiResponse;
import com.klu.project.dto.response.AttendanceResponseDTO;
import com.klu.project.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Attendance", description = "Attendance management APIs")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/api/faculty/attendance")
    @Operation(summary = "Mark attendance for a course (Faculty only, bulk insert)")
    public ResponseEntity<ApiResponse<List<AttendanceResponseDTO>>> markAttendance(
            @Valid @RequestBody AttendanceRequestDTO request,
            Authentication authentication) {
        List<AttendanceResponseDTO> response = attendanceService.markAttendance(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Attendance marked successfully", response));
    }

    @GetMapping("/api/student/attendance")
    @Operation(summary = "Get student's attendance records")
    public ResponseEntity<ApiResponse<Page<AttendanceResponseDTO>>> getStudentAttendance(
            @RequestParam(required = false) Long courseId,
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication) {
        Page<AttendanceResponseDTO> attendance = attendanceService
                .getStudentAttendance(authentication.getName(), courseId, pageable);
        return ResponseEntity.ok(ApiResponse.success("Attendance retrieved successfully", attendance));
    }
}
