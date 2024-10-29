package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.User.ReqSigninDto;
import com.finalProject.Back.dto.response.*;
import com.finalProject.Back.dto.response.User.RespSigninDto;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private UserMapper userMapper;


    public List<RespGetUserDto> getUsers(){
        return ownerMapper.getUsers();
    }

    public List<RespGetOwnerDto> getOwners(){
        return ownerMapper.getOwners();
    }

    @Transactional(rollbackFor = SQLException.class)
    public Long deleteUser(Long id){
        commentMapper.deleteByUserId(id);
        reviewMapper.deleteByUserId(id);
        boardMapper.deleteByUserId(id);
        return ownerMapper.deleteUser(id);
    }

    public int deleteCafe(int id){
        return ownerMapper.deleteCafe(id);
    }

    public RespInfoDto getInfo(Long id){
        RespUserInfoDto userInfoDto = userMapper.findUserInfoById(id);
        List<RespBoardInfoDto> boardInfoDto = boardMapper.getBoardInfoById(id);
        List<RespCommentInfoDto> commentInfoDto = commentMapper.findCommentById(id);
        List<RespReviewInfoDto> reviewInfoDto = reviewMapper.getReviewInfoById(id);
        List<RespBoardCommentInfoDto> boardCommentInfoDto = commentMapper.findBoardCommentById(id);

        return RespInfoDto.builder()
                .user(userInfoDto)
                .board(boardInfoDto)
                .review(reviewInfoDto)
                .comment(commentInfoDto)
                .boardComment(boardCommentInfoDto)
                .build();
    }
}
