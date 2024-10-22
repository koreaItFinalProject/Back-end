package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqReviewDto;
import com.finalProject.Back.dto.response.RespReviewDto;
import com.finalProject.Back.entity.Comment;
import com.finalProject.Back.entity.Review;
import com.finalProject.Back.exception.AccessDeniedException;
import com.finalProject.Back.repository.ReviewMapper;
import com.finalProject.Back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public void write(ReqReviewDto.ReqWriteDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        reviewMapper.save(dto.toEntity(principalUser.getId()));
    }

    public RespReviewDto getList(Long cafeId) {
        List<Review> reviews = reviewMapper.findByCafeId(cafeId);
        int reviewCount = reviewMapper.getCountByCafeId(cafeId);
        return RespReviewDto.builder()
                .reviews(reviews)
                .reviewCount(reviewCount)
                .build();
    }

    public void modify(ReqReviewDto.ReqModifyDto dto) {
        authorityCheck(dto.getReviewId());
        reviewMapper.modify(dto.toEntity());
    }

    private void authorityCheck(Long reviewId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Long writerId = reviewMapper.findById(reviewId);
        if(!principalUser.getId().equals(writerId)) {
            throw new AccessDeniedException();
        }
    }
}
