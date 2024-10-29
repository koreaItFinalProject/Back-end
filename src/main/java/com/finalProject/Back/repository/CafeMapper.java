package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Cafe.Cafe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CafeMapper{
    int save(Cafe cafe);
    Cafe findById(Long cafeId);
    Cafe findByUserId(Long id);
    List<Cafe> findBySearchAndCategory(@Param("category") String category, @Param("search") String search);
    int modifyCafeInfo(Cafe cafe);
    int modifyBannerImg(Cafe cafe);
}
