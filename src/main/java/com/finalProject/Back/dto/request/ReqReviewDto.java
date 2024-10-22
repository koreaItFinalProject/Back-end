package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Review;
import lombok.Builder;
import lombok.Data;

public class ReqReviewDto {

    @Data
    @Builder
    public static class ReqWriteDto {
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

    @Data
    @Builder
    public static class ReqModifyDto {
        private Long reviewId;
        private int rating;
        private String category;
        private String review;

        public Review toEntity() {
            return Review.builder()
                    .id(reviewId)
                    .rating(rating)
                    .category(category)
                    .review(review)
                    .build();
        }
    }
}
