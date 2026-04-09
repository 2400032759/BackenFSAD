package com.klu.project.controller;

import com.klu.project.dto.request.ChatMessageRequestDTO;
import com.klu.project.dto.response.ApiResponse;
import com.klu.project.dto.response.ChatMessageResponseDTO;
import com.klu.project.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "Chat", description = "Messaging and group chat APIs")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chat")
    public ChatMessageResponseDTO sendMessage(@Payload ChatMessageRequestDTO request,
                                               Principal principal) {
        String username = principal != null ? principal.getName() : "anonymous";
        return chatService.saveMessage(request, username);
    }

    @GetMapping("/api/chat/messages/{groupName}")
    @Operation(summary = "Get chat message history for a group")
    public ResponseEntity<ApiResponse<List<ChatMessageResponseDTO>>> getGroupMessages(
            @PathVariable String groupName) {
        List<ChatMessageResponseDTO> messages = chatService.getGroupMessages(groupName);
        return ResponseEntity.ok(ApiResponse.success("Messages retrieved successfully", messages));
    }
}
