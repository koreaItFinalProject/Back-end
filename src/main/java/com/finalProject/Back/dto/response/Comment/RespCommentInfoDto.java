package com.finalProject.Back.dto.response.Comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespCommentInfoDto {
    private Long id;
    private Long board_id;
    private String content;
    private String writeDate;
}
