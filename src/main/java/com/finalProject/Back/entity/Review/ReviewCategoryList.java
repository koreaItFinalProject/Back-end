package com.finalProject.Back.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewCategoryList {
    private Long reviewId;
    private List<Long> categoryId;
}
