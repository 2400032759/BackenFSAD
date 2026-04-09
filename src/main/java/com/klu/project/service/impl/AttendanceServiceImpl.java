package com.klu.project.service.impl;

import com.klu.project.dto.request.AttendanceRequestDTO;
import com.klu.project.dto.response.AttendanceResponseDTO;
import com.klu.project.entity.*;
import com.klu.project.exception.BadRequestException;
import com.klu.project.exception.ResourceNotFoundException;
import com.klu.project.mapper.AttendanceMapper;
import com.klu.project.repository.AttendanceRepository;
import com.klu.project.repository.CourseRepository;
import com.klu.project.repository.UserRepository;
import com.klu.project.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AttendanceServiceImpl.class);

    private final AttendanceRepository attendanceRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, CourseRepository courseRepository,
                                  UserRepository userRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    @Transactional
    public List<AttendanceResponseDTO> markAttendance(AttendanceRequestDTO request, String username) {
        User faculty = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        if (faculty.getRole() != Role.FACULTY) {
            throw new BadRequestException("Only faculty can mark attendance");
        }

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", request.getCourseId()));

        List<Attendance> savedRecords = new ArrayList<>();

        for (AttendanceRequestDTO.AttendanceRecordDTO record : request.getRecords()) {
            User student = userRepository.findById(record.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student", "id", record.getStudentId()));

            if (attendanceRepository.existsByStudentIdAndCourseIdAndDate(
                    student.getId(), course.getId(), request.getDate())) {
                log.warn("Attendance already marked for student {} on {}", student.getId(), request.getDate());
                continue;
            }

            Attendance attendance = new Attendance(
                    student,
                    course,
                    request.getDate(),
                    AttendanceStatus.valueOf(record.getStatus())
            );

            savedRecords.add(attendanceRepository.save(attendance));
        }

        log.info("Attendance marked for {} students in course {} by {}", savedRecords.size(), course.getCode(), username);
        return attendanceMapper.toDTOList(savedRecords);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AttendanceResponseDTO> getStudentAttendance(String username, Long courseId, Pageable pageable) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        if (courseId != null) {
            return attendanceRepository.findByStudentIdAndCourseId(student.getId(), courseId, pageable)
                    .map(attendanceMapper::toDTO);
        }
        return attendanceRepository.findByStudentId(student.getId(), pageable)
                .map(attendanceMapper::toDTO);
    }
}
