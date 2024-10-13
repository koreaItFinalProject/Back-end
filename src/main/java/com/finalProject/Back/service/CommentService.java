package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqCommentDto;
import com.finalProject.Back.dto.response.RespCommentDto;
import com.finalProject.Back.repository.CommentMapper;
import com.finalProject.Back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void write(ReqCommentDto.ReqWriteCommentDto dto) {
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

}
