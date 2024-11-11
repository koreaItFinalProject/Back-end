package com.finalProject.Back.dto.response.Review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespReviewInfoDto {
    private Long id;
    private Long cafeId;
    private String cafeName;
    private String category;
    private String img;
    private Long writerId;
    private int rating;
    private String review;
    private String writeDate;
}
