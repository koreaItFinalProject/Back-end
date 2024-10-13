package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int save(Comment comment);
    List<Comment> findAllByBoardId(Long boardId);
    int getCountByBoardId(Long boardId);
}
