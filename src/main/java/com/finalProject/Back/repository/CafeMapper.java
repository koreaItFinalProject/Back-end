package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Cafe.Cafe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CafeMapper{
    int save(Cafe cafe);
    Cafe findById(Long cafeId);
    Long findByUserId(Long userId);
    List<Cafe> findBySearchAndCategory(@Param("category") String category, @Param("search") String search);
}
