package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.board.Board;
import lombok.Builder;
import lombok.Data;

public class ReqBoardDto {

    @Data
    @Builder
    public static class WriteBoardDto {
        private String title;
        private String content;

        public Board toEntity(Long userId) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .writerId(userId)
                    .build();
        }
    }

    @Data
    @Builder
    public static class ModifyBoardDto {
        private Long boardId;
        private String title;
        private String content;

        public Board toEntity() {
            return Board.builder()
                    .id(boardId)
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Data
    public static class BoardListDto {
        private Long page;
        private Long limit;
        private String searchFilter;
        private String searchValue;
    }

}
