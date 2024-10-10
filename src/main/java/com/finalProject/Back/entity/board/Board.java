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
    private int viewCount;
    private LocalDate writeDate;
}
