package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Board;
import lombok.Builder;
import lombok.Data;

public class ReqBoardDto {

    @Data
    @Builder
    public static class WriteBoardDto {
        private String title;
        private String content;

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }
}
