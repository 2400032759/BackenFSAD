package com.klu.project.mapper;

import com.klu.project.dto.response.CourseMaterialResponseDTO;
import com.klu.project.entity.CourseMaterial;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMaterialMapper {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.name", target = "courseName")
    @Mapping(source = "uploadedBy.id", target = "uploadedById")
    @Mapping(source = "uploadedBy.fullName", target = "uploadedByName")
    CourseMaterialResponseDTO toDTO(CourseMaterial material);

    List<CourseMaterialResponseDTO> toDTOList(List<CourseMaterial> materials);
}
