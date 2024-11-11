package com.finalProject.Back.dto.response;

import com.finalProject.Back.entity.Comment;
import com.finalProject.Back.entity.Review.Review;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.entity.board.Board;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespRecentDto {
    private List<Review> reviewList;
    private List<Board> boardList;
    private List<Comment> commentList;
    private List<User> userList;
}
