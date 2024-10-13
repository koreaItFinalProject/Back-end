package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Comment;
import lombok.Builder;
import lombok.Data;

public class ReqCommentDto {

    @Data
    @Builder
    public static class WriteDto {
        private Long boardId;
        private Long parentId;
        private String content;

        public Comment toEntity(Long userId) {
            return Comment.builder()
                    .boardId(boardId)
                    .parentId(parentId)
                    .content(content)
                    .writerId(userId)
                    .build();
        }
    }

    @Data
    @Builder
    public static class ModifyDto {
        private Long commentId;
        private String content;

        public Comment toEntity() {
            return Comment.builder()
                    .id(commentId)
                    .content(content)
                    .build();
        }
    }

}
