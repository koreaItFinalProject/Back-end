package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqReviewDto;
import com.finalProject.Back.entity.Cafe.CafeDetail;
import com.finalProject.Back.entity.Review.Review;
import com.finalProject.Back.entity.Review.ReviewCategory;
import com.finalProject.Back.exception.AccessDeniedException;
import com.finalProject.Back.repository.ReviewCategoryMapper;
import com.finalProject.Back.repository.ReviewMapper;
import com.finalProject.Back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewCategoryMapper reviewCategoryMapper;

    @Transactional(rollbackFor = SQLException.class)
    public void write(ReqReviewDto.ReqWriteDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Review review = dto.toEntity(principalUser.getId());
        reviewMapper.save(review);
        Long reviewId = review.getId();

        List<Long> categoryIds = dto.getCategoryIds();
        for(Long categoryId: categoryIds) {
            ReviewCategory reviewCategory = new ReviewCategory(reviewId, categoryId);
            reviewCategoryMapper.save(reviewCategory);
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modify(ReqReviewDto.ReqModifyDto dto) {
        authorityCheck(dto.getReviewId());
        if(dto.getRating() == null && dto.getCategoryIds() == null) {
            dto.setRating(reviewMapper.reviewRating(dto.getReviewId()));
            dto.setCategoryIds(reviewCategoryMapper.findCategory(dto.getReviewId()));
        }
        reviewMapper.modify(dto.toEntity());
        List<Long> categoryIds = dto.getCategoryIds();
        Long reviewId = dto.getReviewId();

        reviewCategoryMapper.delete(reviewId);
        for(Long categoryId: categoryIds) {
            ReviewCategory reviewCategory = new ReviewCategory(reviewId, categoryId);
            reviewCategoryMapper.save(reviewCategory);
        }
    }

    public void delete(Long reviewId) {
        authorityCheck(reviewId);
        reviewMapper.delete(reviewId);
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
