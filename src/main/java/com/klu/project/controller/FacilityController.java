package com.klu.project.controller;

import com.klu.project.dto.response.ApiResponse;
import com.klu.project.dto.response.HostelResponseDTO;
import com.klu.project.dto.response.TransportResponseDTO;
import com.klu.project.service.FacilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/facilities")
@Tag(name = "Facilities", description = "Hostel and Transport management APIs")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("/hostel")
    @Operation(summary = "Get student's hostel information")
    public ResponseEntity<ApiResponse<HostelResponseDTO>> getHostelInfo(
            Authentication authentication) {
        HostelResponseDTO hostel = facilityService.getHostelInfo(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Hostel info retrieved successfully", hostel));
    }

    @GetMapping("/transport")
    @Operation(summary = "Get student's transport information")
    public ResponseEntity<ApiResponse<TransportResponseDTO>> getTransportInfo(
            Authentication authentication) {
        TransportResponseDTO transport = facilityService.getTransportInfo(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Transport info retrieved successfully", transport));
    }
}
