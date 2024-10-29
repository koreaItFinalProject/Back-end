package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Review.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ReqReviewDto {

    @Data
    @Builder
    public static class ReqWriteDto {
        private Long cafeId;
        private Double rating;
        private String review;
        private List<Long> categoryIds;

        public Review toEntity(Long userId) {
            return Review.builder()
                    .cafeId(cafeId)
                    .writerId(userId)
                    .rating(rating)
                    .review(review)
                    .build();
        }
    }

    @Data
    @Builder
    public static class ReqModifyDto {
        private Long reviewId;
        private Double rating;
        private String review;
        private List<Long> categoryIds;

        public Review toEntity() {
            return Review.builder()
                    .id(reviewId)
                    .rating(rating)
                    .review(review)
                    .build();
        }
    }
}
