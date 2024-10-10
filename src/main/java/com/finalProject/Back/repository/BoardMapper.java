package com.finalProject.Back.repository;

import com.finalProject.Back.entity.board.Board;
import com.finalProject.Back.entity.board.BoardList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    int save(Board board);
    int modify(Board board);
    int delete(Long boardId);
    List<BoardList> findAllByStartIndexAndLimit(
            @Param("startIndex") Long startIndex,
            @Param("limit") Long limit);
    Board getDetail(Long boardId);
    int getTotalCount();
    int addViewCountById(Long boardId);

}
