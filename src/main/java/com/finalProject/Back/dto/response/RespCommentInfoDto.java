package com.finalProject.Back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespCommentInfoDto {
    private Long board_id;
    private String comment;
    private String write_date;
}
