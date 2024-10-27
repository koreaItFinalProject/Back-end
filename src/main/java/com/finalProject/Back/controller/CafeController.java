package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqCafeDto;
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
    public ResponseEntity<?> add(@RequestBody ReqCafeDto dto){
        System.out.println(dto);
        return ResponseEntity.ok().body(cafeService.addCafe(dto));
    }

    @GetMapping("/cafe/get/{category}")
    public ResponseEntity<?> get(@PathVariable String category){
        System.out.println(cafeService.get(category));
        return ResponseEntity.ok().body(cafeService.get(category));
    }

    @GetMapping("/cafe/get/{category}/{search}")
    public ResponseEntity<?> get(@PathVariable String category, @PathVariable String search){
        return ResponseEntity.ok().body(cafeService.getList(category, search));
    }

    @GetMapping("/cafe/{cafeId}")
    public ResponseEntity<?> getLike(@PathVariable Long cafeId) {
        System.out.println(cafeService.getLike(cafeId));
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
