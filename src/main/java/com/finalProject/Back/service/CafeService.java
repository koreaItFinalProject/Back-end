package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqCafeDto;
import com.finalProject.Back.dto.request.ReqGetCafeDto;
import com.finalProject.Back.dto.response.RespCafeListDto;
import com.finalProject.Back.entity.Cafe;
import com.finalProject.Back.repository.CafeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CafeService {

    @Autowired
    private CafeMapper cafeMapper;

    public int addCafe(ReqCafeDto dto) {
        return cafeMapper.save(dto.toEntity());
    }

    public List<Cafe> get(String category){
        return cafeMapper.findByCategory(category);
    }

    public List<Cafe> getList(String category, String search){
        return cafeMapper.findBySearchAndCategory(category, search);
    }
}
