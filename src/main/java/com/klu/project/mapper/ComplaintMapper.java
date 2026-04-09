package com.klu.project.mapper;

import com.klu.project.dto.response.ComplaintResponseDTO;
import com.klu.project.entity.Complaint;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.fullName", target = "studentName")
    ComplaintResponseDTO toDTO(Complaint complaint);

    List<ComplaintResponseDTO> toDTOList(List<Complaint> complaints);
}
