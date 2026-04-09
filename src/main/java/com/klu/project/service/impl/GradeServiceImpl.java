package com.klu.project.service.impl;

import com.klu.project.dto.request.GradeRequestDTO;
import com.klu.project.dto.response.GradeResponseDTO;
import com.klu.project.entity.*;
import com.klu.project.exception.BadRequestException;
import com.klu.project.exception.DuplicateResourceException;
import com.klu.project.exception.ResourceNotFoundException;
import com.klu.project.mapper.GradeMapper;
import com.klu.project.repository.CourseRepository;
import com.klu.project.repository.GradeRepository;
import com.klu.project.repository.UserRepository;
import com.klu.project.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GradeServiceImpl implements GradeService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GradeServiceImpl.class);

    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final GradeMapper gradeMapper;

    public GradeServiceImpl(GradeRepository gradeRepository, CourseRepository courseRepository,
                            UserRepository userRepository, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.gradeMapper = gradeMapper;
    }

    @Override
    @Transactional
    public GradeResponseDTO assignGrade(GradeRequestDTO request, String username) {
        User faculty = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        if (faculty.getRole() != Role.FACULTY) {
            throw new BadRequestException("Only faculty can assign grades");
        }

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", request.getStudentId()));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", request.getCourseId()));

        if (gradeRepository.existsByStudentIdAndCourseId(student.getId(), course.getId())) {
            throw new DuplicateResourceException("Grade already assigned for this student in this course");
        }

        Grade grade = new Grade(
                student,
                course,
                request.getMarks(),
                request.getGrade(),
                GradeStatus.valueOf(request.getStatus())
        );

        grade = gradeRepository.save(grade);
        log.info("Grade assigned: {} for student {} in course {} by {}", 
                request.getGrade(), student.getUsername(), course.getCode(), username);
        return gradeMapper.toDTO(grade);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GradeResponseDTO> getStudentGrades(String username, Pageable pageable) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return gradeRepository.findByStudentId(student.getId(), pageable)
                .map(gradeMapper::toDTO);
    }
}
