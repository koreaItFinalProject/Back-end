package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Cafe;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CafeMapper {
    int save(Cafe cafe);
}
