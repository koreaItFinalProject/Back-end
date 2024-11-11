package com.finalProject.Back.repository;

import com.finalProject.Back.dto.response.Cafe.RespAdminCafeListDto;
import com.finalProject.Back.dto.response.Review.RespReviewInfoDto;
import com.finalProject.Back.entity.Cafe.Cafe;
import com.finalProject.Back.entity.Cafe.CafeDetail;
import com.finalProject.Back.entity.Cafe.CafeMenuImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CafeMapper{
    int save(Cafe cafe);
    int saveDefaultCafeMenu(int cafeId);
    Cafe findByUserId(Long id);
    CafeDetail findByCafeId(Long cafeId);
    List<Cafe> findBySearchAndCategory(@Param("category") String category, @Param("search") String search);
    int modifyCafeInfo(Cafe cafe);
    int modifyBannerImg(Cafe cafe);
    int modifyCafeMenuImg(CafeMenuImg cafeMenuImg);
    List<RespAdminCafeListDto> getCafeList();

}
