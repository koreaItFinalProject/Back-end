package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int save(Comment comment);
    int getCountByBoardId(Long boardId);
    int modifyById(Comment comment);
    int deleteById(Long commentId);
    List<Comment> findAllByBoardId(Long boardId);
    Comment findById(Long commentId);
}
