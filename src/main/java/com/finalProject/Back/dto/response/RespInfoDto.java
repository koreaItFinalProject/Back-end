package com.finalProject.Back.dto.response;

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
}
