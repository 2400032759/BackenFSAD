package com.klu.project.mapper;

import com.klu.project.dto.response.FeeResponseDTO;
import com.klu.project.entity.FeeRecord;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeeRecordMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.fullName", target = "studentName")
    FeeResponseDTO toDTO(FeeRecord feeRecord);

    List<FeeResponseDTO> toDTOList(List<FeeRecord> feeRecords);
}
