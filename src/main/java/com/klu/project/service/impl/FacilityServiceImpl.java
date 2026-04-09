package com.klu.project.service.impl;

import com.klu.project.dto.response.HostelResponseDTO;
import com.klu.project.dto.response.TransportResponseDTO;
import com.klu.project.entity.Hostel;
import com.klu.project.entity.Transport;
import com.klu.project.entity.User;
import com.klu.project.exception.ResourceNotFoundException;
import com.klu.project.mapper.HostelMapper;
import com.klu.project.mapper.TransportMapper;
import com.klu.project.repository.HostelRepository;
import com.klu.project.repository.TransportRepository;
import com.klu.project.repository.UserRepository;
import com.klu.project.service.FacilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class FacilityServiceImpl implements FacilityService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FacilityServiceImpl.class);

    private final HostelRepository hostelRepository;
    private final TransportRepository transportRepository;
    private final UserRepository userRepository;
    private final HostelMapper hostelMapper;
    private final TransportMapper transportMapper;

    public FacilityServiceImpl(HostelRepository hostelRepository, TransportRepository transportRepository,
                               UserRepository userRepository, HostelMapper hostelMapper, TransportMapper transportMapper) {
        this.hostelRepository = hostelRepository;
        this.transportRepository = transportRepository;
        this.userRepository = userRepository;
        this.hostelMapper = hostelMapper;
        this.transportMapper = transportMapper;
    }

    @Override
    public HostelResponseDTO getHostelInfo(String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        Hostel hostel = hostelRepository.findByStudentId(student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Hostel record not found for student: " + username));
        return hostelMapper.toDTO(hostel);
    }

    @Override
    public TransportResponseDTO getTransportInfo(String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        Transport transport = transportRepository.findByStudentId(student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transport record not found for student: " + username));
        return transportMapper.toDTO(transport);
    }
}
