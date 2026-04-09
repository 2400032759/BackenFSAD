package com.klu.project.mapper;

import com.klu.project.dto.response.EnrollmentResponseDTO;
import com.klu.project.entity.Enrollment;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.fullName", target = "studentName")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.name", target = "courseName")
    @Mapping(source = "course.code", target = "courseCode")
    EnrollmentResponseDTO toDTO(Enrollment enrollment);

    List<EnrollmentResponseDTO> toDTOList(List<Enrollment> enrollments);
}
