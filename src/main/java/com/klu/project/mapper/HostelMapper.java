package com.klu.project.mapper;

import com.klu.project.dto.response.HostelResponseDTO;
import com.klu.project.entity.Hostel;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HostelMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.fullName", target = "studentName")
    HostelResponseDTO toDTO(Hostel hostel);
}
