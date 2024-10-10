package com.finalProject.Back.repository;

import com.finalProject.Back.entity.board.Board;
import com.finalProject.Back.entity.board.BoardList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    int save(Board board);
    List<BoardList> findAllByStartIndexAndLimit(
            @Param("startIndex") Long startIndex,
            @Param("limit") Long limit);
    int getTotalCount();

}
