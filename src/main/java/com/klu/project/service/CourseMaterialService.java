package com.klu.project.service;

import com.klu.project.dto.response.CourseMaterialResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseMaterialService {

    CourseMaterialResponseDTO uploadMaterial(Long courseId, MultipartFile file, String username);

    Resource downloadMaterial(Long materialId);

    void deleteMaterial(Long materialId, String username);

    List<CourseMaterialResponseDTO> getMaterialsByCourse(Long courseId);
}
