package com.finalProject.Back.entity.Cafe;

import com.finalProject.Back.entity.Review.Review;
import com.finalProject.Back.entity.Review.ReviewCategoryCount;
import com.finalProject.Back.entity.Review.ReviewCategoryList;
import com.finalProject.Back.entity.board.BoardList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CafeDetail {
    private Long id;
    private Long ownerId;
    private String cafeName;
    private String address;
    private String lat;
    private String lng;
    private String category;
    private String img;
    private int reviewCount;
    private Double totalRating;
    private String menu1;
    private String menu2;
    private List<ReviewCategoryCount> reviewCategoryCounts;
    private List<Review> reviews;
    private List<ReviewCategoryList> reviewCategories;
    private List<BoardList> noticeList;
}
