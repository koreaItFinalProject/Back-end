package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqReviewDto;
import com.finalProject.Back.dto.response.RespReviewDto;
import com.finalProject.Back.entity.Review;
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

    public void write(ReqReviewDto.ReqWriteReviewDto dto) {
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
}
