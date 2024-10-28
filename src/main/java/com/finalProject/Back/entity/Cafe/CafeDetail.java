package com.finalProject.Back.entity.Cafe;

import com.finalProject.Back.entity.Review;
import com.finalProject.Back.entity.ReviewCategoryCount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CafeDetail {
    private Long id;
    private Long owner_id;
    private String cafeName;
    private String address;
    private String lat;
    private String lng;
    private String category;
    private int reviewCount;
    private Double totalRating;
    private List<ReviewCategoryCount> reviewCategoryCounts;
    private List<Review> reviews;
}
