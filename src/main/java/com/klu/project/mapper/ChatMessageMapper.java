package com.klu.project.mapper;

import com.klu.project.dto.response.ChatMessageResponseDTO;
import com.klu.project.entity.ChatMessage;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "sender.fullName", target = "senderName")
    ChatMessageResponseDTO toDTO(ChatMessage chatMessage);

    List<ChatMessageResponseDTO> toDTOList(List<ChatMessage> messages);
}
