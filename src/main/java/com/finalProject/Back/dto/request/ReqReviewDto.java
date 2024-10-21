package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Review;
import lombok.Builder;
import lombok.Data;

public class ReqReviewDto {

    @Data
    @Builder
    public static class ReqWriteReviewDto {
        private Long cafeId;
        private int rating;
        private String category;
        private String review;

        public Review toEntity(Long userId) {
            return Review.builder()
                    .cafeId(cafeId)
                    .writerId(userId)
                    .rating(rating)
                    .category(category)
                    .review(review)
                    .build();
        }
    }
}
