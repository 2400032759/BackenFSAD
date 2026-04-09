package com.klu.project.mapper;

import com.klu.project.dto.response.GradeResponseDTO;
import com.klu.project.entity.Grade;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.fullName", target = "studentName")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.name", target = "courseName")
    GradeResponseDTO toDTO(Grade grade);

    List<GradeResponseDTO> toDTOList(List<Grade> grades);
}
