package com.klu.project.service.impl;

import com.klu.project.dto.request.CourseRequestDTO;
import com.klu.project.dto.request.EnrollmentRequestDTO;
import com.klu.project.dto.response.CourseResponseDTO;
import com.klu.project.dto.response.EnrollmentResponseDTO;
import com.klu.project.dto.response.UserSummaryDTO;
import com.klu.project.entity.Course;
import com.klu.project.entity.Enrollment;
import com.klu.project.entity.Role;
import com.klu.project.entity.User;
import com.klu.project.exception.BadRequestException;
import com.klu.project.exception.DuplicateResourceException;
import com.klu.project.exception.ResourceNotFoundException;
import com.klu.project.mapper.CourseMapper;
import com.klu.project.mapper.EnrollmentMapper;
import com.klu.project.repository.CourseRepository;
import com.klu.project.repository.EnrollmentRepository;
import com.klu.project.repository.UserRepository;
import com.klu.project.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;
    private final EnrollmentMapper enrollmentMapper;

    public CourseServiceImpl(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository,
                             UserRepository userRepository, CourseMapper courseMapper, EnrollmentMapper enrollmentMapper) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseMapper = courseMapper;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    @Transactional
    public CourseResponseDTO createCourse(CourseRequestDTO request, String username) {
        User creator = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        User faculty;
        if ((creator.getRole() == Role.ADMIN || creator.getRole() == Role.ADMINISTRATOR) && request.getFacultyId() != null) {
            faculty = userRepository.findById(request.getFacultyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Faculty", "id", request.getFacultyId()));
        } else {
            faculty = creator;
        }

        if (faculty.getRole() != Role.FACULTY) {
            throw new BadRequestException("Assigned user must have FACULTY role");
        }

        if (courseRepository.existsByCodeAndSection(request.getCode(), request.getSection())) {
            throw new DuplicateResourceException("Section already exists: " + request.getCode() + " - " + request.getSection());
        }

        Course course = new Course(
                request.getName(),
                request.getCode(),
                faculty,
                request.getSection(),
                request.getRoom()
        );

        course = courseRepository.save(course);
        log.info("Course created: {} assigned to faculty: {}", course.getCode(), faculty.getUsername());
        return courseMapper.toDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserSummaryDTO> getUsersByRole(String roleName) {
        Role role = Role.valueOf(roleName.toUpperCase().trim());
        List<User> users = userRepository.findByRole(role);
        List<UserSummaryDTO> dtos = new java.util.ArrayList<>();
        for (User u : users) {
             dtos.add(new UserSummaryDTO(u.getId(), u.getUsername(), u.getFullName(), u.getRole().toString()));
        }
        return dtos;
    }

    @Override
    @Transactional
    public EnrollmentResponseDTO enrollStudentByAdmin(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));

        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new DuplicateResourceException("Student is already enrolled");
        }

        Enrollment enrollment = new Enrollment(student, course);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDTO(enrollment);
    }

    @Override
    @Transactional
    public void removeEnrollmentByAdmin(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CourseResponseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable).map(courseMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return courseMapper.toDTO(course);
    }

    @Override
    @Transactional
    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO request, String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        if (student.getRole() != Role.STUDENT) {
            throw new BadRequestException("Only students can enroll in courses");
        }

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", request.getCourseId()));

        if (enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), course.getId())) {
            throw new DuplicateResourceException("Student is already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(student, course);

        enrollment = enrollmentRepository.save(enrollment);
        log.info("Student {} enrolled in course {}", username, course.getCode());
        return enrollmentMapper.toDTO(enrollment);
    }

    @Override
    @Transactional
    public void unenrollStudent(Long courseId, String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Enrollment enrollment = enrollmentRepository
                .findByStudentIdAndCourseId(student.getId(), courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        enrollmentRepository.delete(enrollment);
        log.info("Student {} unenrolled from course ID {}", username, courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDTO> getStudentEnrollments(String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(student.getId());
        return enrollmentMapper.toDTOList(enrollments);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserSummaryDTO> getEnrolledStudents(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        List<UserSummaryDTO> dtos = new java.util.ArrayList<>();
        for (Enrollment e : enrollments) {
            User student = e.getStudent();
            dtos.add(new UserSummaryDTO(student.getId(), student.getUsername(), student.getFullName(), student.getRole().toString()));
        }
        return dtos;
    }
}
