package com.klu.project.service.impl;

import com.klu.project.dto.request.ChatMessageRequestDTO;
import com.klu.project.dto.response.ChatMessageResponseDTO;
import com.klu.project.entity.ChatMessage;
import com.klu.project.entity.User;
import com.klu.project.exception.ResourceNotFoundException;
import com.klu.project.mapper.ChatMessageMapper;
import com.klu.project.repository.ChatMessageRepository;
import com.klu.project.repository.UserRepository;
import com.klu.project.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ChatServiceImpl.class);

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatMessageMapper chatMessageMapper;

    public ChatServiceImpl(ChatMessageRepository chatMessageRepository, UserRepository userRepository, ChatMessageMapper chatMessageMapper) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Override
    @Transactional
    public ChatMessageResponseDTO saveMessage(ChatMessageRequestDTO request, String username) {
        User sender = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        ChatMessage message = new ChatMessage(
                sender,
                request.getGroupName(),
                request.getMessage(),
                java.time.LocalDateTime.now()
        );

        message = chatMessageRepository.save(message);
        log.debug("Chat message saved from {} in group {}", username, request.getGroupName());
        return chatMessageMapper.toDTO(message);
    }

    @Override
    public List<ChatMessageResponseDTO> getGroupMessages(String groupName) {
        List<ChatMessage> messages = chatMessageRepository
                .findByGroupNameOrderByTimestampAsc(groupName);
        return chatMessageMapper.toDTOList(messages);
    }
}
