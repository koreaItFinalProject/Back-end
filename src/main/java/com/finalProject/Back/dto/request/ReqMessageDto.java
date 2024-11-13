package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Message;
import lombok.Data;

@Data
public class ReqMessageDto {
    private String type;
    private String content;
    private Long userId = 0L;
    private Long ContentId;

    public Message toEntity() {
        return Message.builder()
                .type(type)
                .content(content)
                .userId(userId)
                .contentId(ContentId)
                .build();
    }
}
