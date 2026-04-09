package com.klu.project.service;

import com.klu.project.dto.request.ComplaintRequestDTO;
import com.klu.project.dto.response.ComplaintResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComplaintService {

    ComplaintResponseDTO createComplaint(ComplaintRequestDTO request, String username);

    Page<ComplaintResponseDTO> getComplaints(String username, String role, Pageable pageable);
}
