package com.klu.project.service.impl;

import com.klu.project.dto.request.ComplaintRequestDTO;
import com.klu.project.dto.response.ComplaintResponseDTO;
import com.klu.project.entity.Complaint;
import com.klu.project.entity.ComplaintStatus;
import com.klu.project.entity.User;
import com.klu.project.exception.ResourceNotFoundException;
import com.klu.project.mapper.ComplaintMapper;
import com.klu.project.repository.ComplaintRepository;
import com.klu.project.repository.UserRepository;
import com.klu.project.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ComplaintServiceImpl.class);

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final ComplaintMapper complaintMapper;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, UserRepository userRepository, ComplaintMapper complaintMapper) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
        this.complaintMapper = complaintMapper;
    }

    @Override
    @Transactional
    public ComplaintResponseDTO createComplaint(ComplaintRequestDTO request, String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Complaint complaint = new Complaint(
                student,
                request.getMessage(),
                ComplaintStatus.OPEN
        );

        complaint = complaintRepository.save(complaint);
        log.info("Complaint created by student: {}", username);
        return complaintMapper.toDTO(complaint);
    }

    @Override
    public Page<ComplaintResponseDTO> getComplaints(String username, String role, Pageable pageable) {
        if ("FACULTY".equals(role)) {
            return complaintRepository.findAll(pageable).map(complaintMapper::toDTO);
        }
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return complaintRepository.findByStudentId(student.getId(), pageable)
                .map(complaintMapper::toDTO);
    }
}
