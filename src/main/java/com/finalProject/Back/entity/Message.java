package com.finalProject.Back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long id;
    private String type;
    private String content;
    private String status;
    private String userId;
    private LocalDate messageDate;
    private Long contentId;
}
