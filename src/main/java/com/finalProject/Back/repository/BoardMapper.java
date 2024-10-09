package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Board;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    int save(Board board);
}
