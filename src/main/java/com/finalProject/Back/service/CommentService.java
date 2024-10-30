package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqCommentDto;
import com.finalProject.Back.dto.response.Comment.RespCommentDto;
import com.finalProject.Back.entity.Comment;
import com.finalProject.Back.exception.AccessDeniedException;
import com.finalProject.Back.repository.CommentMapper;
import com.finalProject.Back.security.principal.PrincipalUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void write(ReqCommentDto.WriteDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        commentMapper.save(dto.toEntity(principalUser.getId()));
    }

    public RespCommentDto getAll(Long boardId) {
        return RespCommentDto.builder()
                .comments(commentMapper.findAllByBoardId(boardId))
                .commentCount(commentMapper.getCountByBoardId(boardId))
                .build();
    }

    public void modify(ReqCommentDto.ModifyDto dto) {
        authorityCheck(dto.getCommentId());
        commentMapper.modifyById(dto.toEntity());
    }

    public void delete(Long commentId) {
        authorityCheck(commentId);
        commentMapper.deleteById(commentId);
    }

    private void authorityCheck(Long commentId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Comment comment = commentMapper.findById(commentId);
        if(!principalUser.getId().equals(comment.getWriterId())) {
            throw new AccessDeniedException();
        }
    }

}
