package com.klu.project.controller;

import com.klu.project.dto.request.GradeRequestDTO;
import com.klu.project.dto.response.ApiResponse;
import com.klu.project.dto.response.GradeResponseDTO;
import com.klu.project.service.GradeService;
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

@RestController
@Tag(name = "Grades", description = "Grade management APIs")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/api/faculty/grades")
    @Operation(summary = "Assign grade to a student (Faculty only)")
    public ResponseEntity<ApiResponse<GradeResponseDTO>> assignGrade(
            @Valid @RequestBody GradeRequestDTO request,
            Authentication authentication) {
        GradeResponseDTO response = gradeService.assignGrade(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Grade assigned successfully", response));
    }

    @GetMapping("/api/student/grades")
    @Operation(summary = "Get student's grades")
    public ResponseEntity<ApiResponse<Page<GradeResponseDTO>>> getStudentGrades(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication) {
        Page<GradeResponseDTO> grades = gradeService.getStudentGrades(authentication.getName(), pageable);
        return ResponseEntity.ok(ApiResponse.success("Grades retrieved successfully", grades));
    }
}
