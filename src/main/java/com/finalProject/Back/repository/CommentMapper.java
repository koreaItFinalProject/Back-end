package com.finalProject.Back.repository;

import com.finalProject.Back.dto.response.Comment.RespCommentInfoDto;
import com.finalProject.Back.dto.response.RespBoardCommentInfoDto;
import com.finalProject.Back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int save(Comment comment);
    int getCountByBoardId(Long boardId);
    int modifyById(Comment comment);
    int deleteById(Long commentId);
    int deleteByUserId(Long userId);
    int deleteByBoardId(Long boardId);
    Long findByCommentId(Long commentId);
    List<Comment> findAllByBoardId(Long boardId);
    Comment findById(Long commentId);
    List<RespCommentInfoDto> findCommentById(Long id);
    List<RespBoardCommentInfoDto> findBoardCommentById(Long id);
    List<Comment> getRecent();
}
