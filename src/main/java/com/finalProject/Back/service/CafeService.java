package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqCafeDto;
import com.finalProject.Back.dto.request.ReqGetCafeDto;
import com.finalProject.Back.dto.response.RespCafeDto;
import com.finalProject.Back.entity.Cafe.Cafe;
import com.finalProject.Back.entity.Cafe.CafeLike;
import com.finalProject.Back.repository.CafeLikeMapper;
import com.finalProject.Back.repository.CafeMapper;
import com.finalProject.Back.security.principal.PrincipalUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CafeService {

    @Autowired
    private CafeMapper cafeMapper;
    @Autowired
    private CafeLikeMapper cafeLikeMapper;

    public int addCafe(ReqCafeDto dto) {
        return cafeMapper.save(dto.toEntity());
    }

    public List<Cafe> getList(ReqGetCafeDto dto){
        return cafeMapper.findBySearchAndCategory(dto.getCategory(), dto.getSearch());
    }

    public Cafe getDetail(Long cafeId) {
        return cafeMapper.findById(cafeId);
    }

    public Long getCafeId() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return cafeMapper.findByUserId(principalUser.getId());
    }

    public RespCafeDto.RespCafeLikeDto getLike(Long cafeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = null;
        if(!authentication.getName().equals("anonymousUser")) {
            PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
            userId = principalUser.getId();
        }
        CafeLike cafeLike = cafeLikeMapper.findByCafeIdAndUserId(cafeId, userId);
        int likeCount = cafeLikeMapper.getLikeCountByCafeId(cafeId);
        return RespCafeDto.RespCafeLikeDto.builder()
                .cafeLikeId(cafeLike == null ? 0 : cafeLike.getCafeLikeId())
                .likeCount(likeCount)
                .build();
    }

    public void like(Long cafeId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        CafeLike cafeLike = CafeLike.builder()
                .cafeId(cafeId)
                .userId(principalUser.getId())
                .build();

        cafeLikeMapper.like(cafeLike);
    }

    public void dislike(Long cafeLikeId) {
        cafeLikeMapper.disLike(cafeLikeId);
    }
}
