package com.finalProject.Back.dto.response;

import com.finalProject.Back.dto.response.Board.RespBoardInfoDto;
import com.finalProject.Back.dto.response.Cafe.RespAdminCafeListDto;
import com.finalProject.Back.dto.response.Cafe.RespCafeListDto;
import com.finalProject.Back.dto.response.Comment.RespCommentInfoDto;
import com.finalProject.Back.dto.response.Review.RespReviewInfoDto;
import com.finalProject.Back.entity.Message;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class RespInfoDto {
    private RespUserInfoDto user;
    private List<RespBoardInfoDto> board;
    private List<RespReviewInfoDto> review;
    private List<RespCommentInfoDto> comment;
    private List<RespBoardCommentInfoDto> boardComment;
    private List<RespAdminCafeListDto> cafemanager;
    private List<Message> alarm;
}
