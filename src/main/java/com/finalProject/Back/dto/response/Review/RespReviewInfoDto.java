package com.finalProject.Back.dto.response.Review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespReviewInfoDto {
    private Long cafeId;
    private String cafeName;
    private int rating;
    private String review;
    private String writeDate;
}
