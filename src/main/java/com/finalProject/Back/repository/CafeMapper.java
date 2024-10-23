package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Cafe.Cafe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CafeMapper{
    int save(Cafe cafe);
        // 카테고리에 따라 카페를 찾는 메소드
    List<Cafe> findByCategory(@Param("category") String category);
    List<Cafe> findBySearchAndCategory(@Param("category") String category, @Param("search") String search);
}
