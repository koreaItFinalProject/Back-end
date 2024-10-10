package com.finalProject.Back.entity.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardList {
    private Long id;
    private String title;
    private String content;
    private Integer likeCount;
    private Integer viewCount;
    private LocalDateTime writeDate;
}
