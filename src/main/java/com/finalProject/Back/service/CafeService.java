package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqCafeDto;
import com.finalProject.Back.entity.Cafe;
import com.finalProject.Back.repository.CafeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CafeService {

    @Autowired
    private CafeMapper cafeMapper;

    public int addCafe(ReqCafeDto dto) {
        return cafeMapper.save(dto.toEntity());
    }
}
