package com.finalProject.Back.dto.response.Comment;

import com.finalProject.Back.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespCommentDto {
    private List<Comment> comments;
    private int commentCount;
}
