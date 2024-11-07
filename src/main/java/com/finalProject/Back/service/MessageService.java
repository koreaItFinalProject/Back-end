package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqMessageDto;
import com.finalProject.Back.entity.Message;
import com.finalProject.Back.repository.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public Long save(ReqMessageDto dto) {
        // Convert DTO to Entity
        Message message = dto.toEntity();

        // Save message and get the generated ID
        messageMapper.save(message);

        Long messageId = message.getId();

        // Print the generated ID
        System.out.println("Generated Message ID: " + messageId);

        return messageId;  // Return the generated ID
    }

    public List<Message> findByUserId(Long UserId) {
        return messageMapper.findByUserId(UserId);
    }
}
