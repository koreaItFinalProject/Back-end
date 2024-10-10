package com.finalProject.Back.dto.response;

import com.finalProject.Back.entity.board.BoardList;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class RespBoardDto {

    @Data
    @Builder
    public static class RespBoardListDto {
        private List<BoardList> boards;
        private Integer totalCount;
    }
}
