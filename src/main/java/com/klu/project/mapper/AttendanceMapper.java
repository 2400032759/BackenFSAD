package com.klu.project.mapper;

import com.klu.project.dto.response.AttendanceResponseDTO;
import com.klu.project.entity.Attendance;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.fullName", target = "studentName")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.name", target = "courseName")
    AttendanceResponseDTO toDTO(Attendance attendance);

    List<AttendanceResponseDTO> toDTOList(List<Attendance> attendances);
}
