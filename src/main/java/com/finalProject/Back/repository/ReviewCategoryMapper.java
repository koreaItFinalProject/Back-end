package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Review.ReviewCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewCategoryMapper {
    int save(ReviewCategory reviewCategory);
    int delete(Long reviewId);
    List<Long> findCategory(Long reviewId);
}
