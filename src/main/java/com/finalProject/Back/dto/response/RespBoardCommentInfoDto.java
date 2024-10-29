package com.finalProject.Back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RespBoardCommentInfoDto {
    private Long boardId; // board id
    private String boardTitle; // board 제목
    private String boardContent; // board 이미지
    private Long commentId; // 댓글 id
    private Long commentWriterId; // 댓글 작성자 id
    private String commentContent; // 댓글 내용
    private LocalDate commentWriteDate; // 댓글 작성일
}
