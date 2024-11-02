package com.finalProject.Back.dto.response.Board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespBoardInfoDto {
    private Long id;
    private String title;
    private String content;
    private String view_count;
    private String writeDate;
    private String category;
}
