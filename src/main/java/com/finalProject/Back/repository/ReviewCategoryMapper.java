package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Review.ReviewCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewCategoryMapper {
    int save(ReviewCategory reviewCategory);
    int delete(Long reviewId);
}
