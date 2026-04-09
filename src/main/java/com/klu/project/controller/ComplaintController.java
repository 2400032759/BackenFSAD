package com.klu.project.controller;

import com.klu.project.dto.request.ComplaintRequestDTO;
import com.klu.project.dto.response.ApiResponse;
import com.klu.project.dto.response.ComplaintResponseDTO;
import com.klu.project.service.ComplaintService;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaints")
@Tag(name = "Complaints", description = "Student grievance management APIs")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    @Operation(summary = "Submit a new complaint")
    public ResponseEntity<ApiResponse<ComplaintResponseDTO>> createComplaint(
            @Valid @RequestBody ComplaintRequestDTO request,
            Authentication authentication) {
        ComplaintResponseDTO response = complaintService.createComplaint(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Complaint submitted successfully", response));
    }

    @GetMapping
    @Operation(summary = "Get complaints (Students see own, Faculty sees all)")
    public ResponseEntity<ApiResponse<Page<ComplaintResponseDTO>>> getComplaints(
            @PageableDefault(size = 10) Pageable pageable,
            Authentication authentication) {
        String role = authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_FACULTY")) ? "FACULTY" : "STUDENT";
        Page<ComplaintResponseDTO> complaints = complaintService
                .getComplaints(authentication.getName(), role, pageable);
        return ResponseEntity.ok(ApiResponse.success("Complaints retrieved successfully", complaints));
    }
}
