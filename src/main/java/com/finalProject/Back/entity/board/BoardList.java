package com.finalProject.Back.entity.board;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardList {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private String img;
    private Integer likeCount;
    private Integer viewCount;
    private LocalDate writeDate;
}
