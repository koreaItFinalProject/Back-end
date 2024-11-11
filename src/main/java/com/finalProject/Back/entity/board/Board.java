package com.finalProject.Back.entity.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
    private Long id;
    private String title;
    private String content;
    private String category;
    private Long writerId;
    private String nickname;
    private String img;
    private Integer commentCount;
    private int viewCount;
    private LocalDate writeDate;
    private String name;
}
