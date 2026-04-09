package com.klu.project.repository;

import com.klu.project.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByGroupNameOrderByTimestampAsc(String groupName);

    Page<ChatMessage> findByGroupNameOrderByTimestampDesc(String groupName, Pageable pageable);
}
