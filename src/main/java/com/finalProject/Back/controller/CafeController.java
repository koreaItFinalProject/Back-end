package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.Cafe.*;
import com.finalProject.Back.service.CafeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @PostMapping("/cafe/add")
    public ResponseEntity<?> add(@RequestBody ReqCafeDto dto) {
        return ResponseEntity.ok().body(cafeService.addCafe(dto));
    }

    @GetMapping("/cafe/get")
    public ResponseEntity<?> getList(ReqGetCafeDto dto) {
        return ResponseEntity.ok().body(cafeService.getList(dto));
    }

    @GetMapping("/cafe/detail/{cafeId}")
    public ResponseEntity<?> getDetails(@PathVariable Long cafeId) {
        return ResponseEntity.ok().body(cafeService.getDetail(cafeId));
    }

    @GetMapping("/cafe/owner")
    public ResponseEntity<?> getCafeInfo() {
        return ResponseEntity.ok().body(cafeService.getCafeInfo());
    }

    @PutMapping("/cafe/{cafeId}")
    public ResponseEntity<?> modifyCafeInfo(@RequestBody ReqCafeInfoModifyDto dto) {
        cafeService.modifyCafeInfo(dto);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/cafe/banner/{cafeId}")
    public ResponseEntity<?> modifyBannerImg(@RequestBody ReqCafeBannerDto dto) {
        cafeService.modifyBannerImg(dto);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/cafe/menu/{cafeId}")
    public ResponseEntity<?> modifyCafeMenuImg(@RequestBody ReqCafeImgDto dto) {
        System.out.println("ReqCafeImgDto" + dto);
        cafeService.modifyCafeMenuImg(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/cafe/{cafeId}")
    public ResponseEntity<?> getLike(@PathVariable Long cafeId) {
        return ResponseEntity.ok().body(cafeService.getLike(cafeId));
    }

    @PostMapping("/cafe/{cafeId}/like")
    public ResponseEntity<?> like(@PathVariable Long cafeId) {
        cafeService.like(cafeId);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/cafe/like/{cafeLikeId}")
    public ResponseEntity<?> disLike(@PathVariable Long cafeLikeId) {
        cafeService.dislike(cafeLikeId);
        return ResponseEntity.ok().body(true);
    }

}
