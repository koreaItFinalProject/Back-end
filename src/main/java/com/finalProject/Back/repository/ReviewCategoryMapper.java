package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewCategoryMapper {
    int save(Long cafeId, List<Category> categories);
}
