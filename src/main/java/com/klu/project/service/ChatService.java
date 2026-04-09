package com.klu.project.service;

import com.klu.project.dto.request.ChatMessageRequestDTO;
import com.klu.project.dto.response.ChatMessageResponseDTO;

import java.util.List;

public interface ChatService {

    ChatMessageResponseDTO saveMessage(ChatMessageRequestDTO request, String username);

    List<ChatMessageResponseDTO> getGroupMessages(String groupName);
}
