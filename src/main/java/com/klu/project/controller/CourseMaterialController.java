package com.klu.project.controller;

import com.klu.project.dto.response.ApiResponse;
import com.klu.project.dto.response.CourseMaterialResponseDTO;
import com.klu.project.service.CourseMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Tag(name = "Course Materials", description = "Upload and manage handouts APIs")
public class CourseMaterialController {

    private final CourseMaterialService materialService;

    public CourseMaterialController(CourseMaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/api/faculty/handouts")
    @Operation(summary = "Upload a course material (Faculty only)")
    public ResponseEntity<ApiResponse<CourseMaterialResponseDTO>> uploadMaterial(
            @RequestParam("courseId") Long courseId,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {
        CourseMaterialResponseDTO response = materialService.uploadMaterial(courseId, file, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Material uploaded successfully", response));
    }

    @GetMapping("/api/files/download/{id}")
    @Operation(summary = "Download a course material by ID")
    public ResponseEntity<Resource> downloadMaterial(@PathVariable Long id) {
        Resource resource = materialService.downloadMaterial(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/api/faculty/handouts/{id}")
    @Operation(summary = "Delete a course material (Faculty only, owner)")
    public ResponseEntity<ApiResponse<Void>> deleteMaterial(
            @PathVariable Long id,
            Authentication authentication) {
        materialService.deleteMaterial(id, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Material deleted successfully"));
    }

    @GetMapping("/api/files/course/{courseId}")
    @Operation(summary = "Get all materials for a course")
    public ResponseEntity<ApiResponse<List<CourseMaterialResponseDTO>>> getMaterialsByCourse(
            @PathVariable Long courseId) {
        List<CourseMaterialResponseDTO> materials = materialService.getMaterialsByCourse(courseId);
        return ResponseEntity.ok(ApiResponse.success("Materials retrieved successfully", materials));
    }
}
