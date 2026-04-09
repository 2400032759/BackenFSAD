package com.klu.project.controller;

import com.klu.project.dto.response.ApiResponse;
import com.klu.project.dto.response.FeeResponseDTO;
import com.klu.project.service.FeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/fees")
@Tag(name = "Fees", description = "Fee management and record APIs")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @GetMapping("/api/student/fees")
    @Operation(summary = "Get student's fee records")
    public ResponseEntity<ApiResponse<Page<FeeResponseDTO>>> getStudentFees(
            @PageableDefault(size = 10) Pageable pageable,
            Authentication authentication) {
        Page<FeeResponseDTO> fees = feeService.getStudentFees(authentication.getName(), pageable);
        return ResponseEntity.ok(ApiResponse.success("Fee records retrieved successfully", fees));
    }

    @GetMapping("/api/fees/receipt/{id}")
    @Operation(summary = "Get fee receipt by ID")
    public ResponseEntity<ApiResponse<FeeResponseDTO>> getFeeReceipt(@PathVariable Long id) {
        FeeResponseDTO receipt = feeService.getFeeReceipt(id);
        return ResponseEntity.ok(ApiResponse.success("Fee receipt retrieved successfully", receipt));
    }
}
