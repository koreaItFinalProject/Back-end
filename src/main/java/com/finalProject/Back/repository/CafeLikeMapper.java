package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Cafe.CafeLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CafeLikeMapper {
    int like(CafeLike cafeLike);
    int disLike(Long cafeLikeId);
    CafeLike findByCafeIdAndUserId(
            @Param("cafeId") Long cafeId,
            @Param("userId") Long userId);
    int getLikeCountByCafeId(Long cafeId);
}
