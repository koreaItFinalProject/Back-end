package com.finalProject.Back.repository;

import com.finalProject.Back.dto.response.Review.RespReviewInfoDto;
import com.finalProject.Back.entity.Review.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    Long save(Review review);
    int getCountByCafeId(Long cafeId);
    int modify(Review review);
    Long findById(Long reviewId);
    int delete(Long reviewId);
    int deleteByUserId(Long userId);
    List<RespReviewInfoDto> getReviewInfoById(Long id);
    List<Review> getRecent();
}
