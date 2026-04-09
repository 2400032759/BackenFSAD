package com.klu.project.service.impl;

import com.klu.project.dto.response.FeeResponseDTO;
import com.klu.project.entity.FeeRecord;
import com.klu.project.entity.User;
import com.klu.project.exception.ResourceNotFoundException;
import com.klu.project.mapper.FeeRecordMapper;
import com.klu.project.repository.FeeRecordRepository;
import com.klu.project.repository.UserRepository;
import com.klu.project.service.FeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FeeServiceImpl.class);

    private final FeeRecordRepository feeRecordRepository;
    private final UserRepository userRepository;
    private final FeeRecordMapper feeRecordMapper;

    public FeeServiceImpl(FeeRecordRepository feeRecordRepository, UserRepository userRepository, FeeRecordMapper feeRecordMapper) {
        this.feeRecordRepository = feeRecordRepository;
        this.userRepository = userRepository;
        this.feeRecordMapper = feeRecordMapper;
    }

    @Override
    public Page<FeeResponseDTO> getStudentFees(String username, Pageable pageable) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return feeRecordRepository.findByStudentId(student.getId(), pageable)
                .map(feeRecordMapper::toDTO);
    }

    @Override
    public FeeResponseDTO getFeeReceipt(Long feeId) {
        FeeRecord feeRecord = feeRecordRepository.findById(feeId)
                .orElseThrow(() -> new ResourceNotFoundException("FeeRecord", "id", feeId));
        return feeRecordMapper.toDTO(feeRecord);
    }
}
