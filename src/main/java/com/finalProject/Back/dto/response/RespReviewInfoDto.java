package com.finalProject.Back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RespReviewInfoDto {
    private Long cafeId;
    private String cafeName;
    private String category;
    private String img;
    private Long writerId;
    private int rating;
    private String review;
    private String writeDate;
}
