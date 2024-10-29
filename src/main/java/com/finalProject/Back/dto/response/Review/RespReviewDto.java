package com.finalProject.Back.dto.response.Review;

import com.finalProject.Back.entity.Review.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespReviewDto {
    private List<Review> reviews;
    private int reviewCount;
}
