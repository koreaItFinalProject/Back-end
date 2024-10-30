package com.finalProject.Back.repository;

<<<<<<< HEAD
import com.finalProject.Back.dto.response.Comment.RespCommentInfoDto;
=======
import com.finalProject.Back.dto.response.RespBoardCommentInfoDto;
import com.finalProject.Back.dto.response.RespCommentInfoDto;
>>>>>>> 847036f7e867932f92d3cd6ec0ad395199891428
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
    List<Comment> findAllByBoardId(Long boardId);
    Comment findById(Long commentId);
    List<RespCommentInfoDto> findCommentById(Long id);
    List<RespBoardCommentInfoDto> findBoardCommentById(Long id);
}
