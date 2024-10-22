package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int save(Review review);
    List<Review> findByCafeId(Long cafeId);
    int getCountByCafeId(Long cafeId);
    int modify(Review review);
    Long findById(Long reviewId);
}
