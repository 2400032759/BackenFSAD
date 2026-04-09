package com.klu.project.mapper;

import com.klu.project.dto.response.TransportResponseDTO;
import com.klu.project.entity.Transport;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransportMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.fullName", target = "studentName")
    TransportResponseDTO toDTO(Transport transport);
}
