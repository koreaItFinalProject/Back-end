package com.finalProject.Back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewCategoryCount {
    private Long cafeId;
    private Long categoryId;
    private int categoryCount;
    private Category category;
}
