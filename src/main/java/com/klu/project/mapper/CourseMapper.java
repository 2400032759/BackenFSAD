package com.klu.project.mapper;

import com.klu.project.dto.response.CourseResponseDTO;
import com.klu.project.entity.Course;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "faculty.id", target = "facultyId")
    @Mapping(source = "faculty.fullName", target = "facultyName")
    CourseResponseDTO toDTO(Course course);

    List<CourseResponseDTO> toDTOList(List<Course> courses);
}
