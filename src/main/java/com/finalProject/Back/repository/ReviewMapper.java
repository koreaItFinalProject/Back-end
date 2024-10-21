package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    int save(Review review);
}
