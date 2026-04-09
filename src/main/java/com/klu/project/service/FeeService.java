package com.klu.project.service;

import com.klu.project.dto.response.FeeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeeService {

    Page<FeeResponseDTO> getStudentFees(String username, Pageable pageable);

    FeeResponseDTO getFeeReceipt(Long feeId);
}
