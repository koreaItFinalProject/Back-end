package com.finalProject.Back.dto.response;

import com.finalProject.Back.entity.board.BoardList;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class RespBoardDto {

    @Data
    @Builder
    public static class RespBoardListDto {
        private List<BoardList> boards;
        private Integer totalCount;
    }

    @Data
    @Builder
    public static class RespBoardDetailDto {
        private Long id;
        private String title;
        private String content;
        private Long writerId;
        private String nickname;
        private int viewCount;
        private LocalDate writeDate;
    }

    @Data
    @Builder
    public static class RespBoardLikeDto {
        private Long boardLikeId;
        private int likeCount;
    }
}
