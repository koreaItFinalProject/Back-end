package com.finalProject.Back.repository;

import com.finalProject.Back.entity.board.BoardLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardLikeMapper {
    int like(BoardLike boardLike);
    int disLike(Long boardLikeId);
    int deleteByBoardId(Long boardId);
    int getLikeCountByBoardId(Long boardId);
    BoardLike findByBoardIdAndUserId(
            @Param("boardId") Long boardId,
            @Param("userId") Long userId);
}
