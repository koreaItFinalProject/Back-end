package com.finalProject.Back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
