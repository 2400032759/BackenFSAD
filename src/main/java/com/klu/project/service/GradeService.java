package com.klu.project.service;

import com.klu.project.dto.request.GradeRequestDTO;
import com.klu.project.dto.response.GradeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GradeService {

    GradeResponseDTO assignGrade(GradeRequestDTO request, String username);

    Page<GradeResponseDTO> getStudentGrades(String username, Pageable pageable);
}
