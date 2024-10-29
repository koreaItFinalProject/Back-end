package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqReviewDto;
import com.finalProject.Back.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<?> write(@RequestBody ReqReviewDto.ReqWriteDto dto) {
        System.out.println(dto);
        reviewService.write(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/review/{cafeId}")
    public ResponseEntity<?> getDetail(@PathVariable Long cafeId) {
        return ResponseEntity.ok().body(reviewService.getDetail(cafeId));
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<?> modify(@RequestBody ReqReviewDto.ReqModifyDto dto) {
        reviewService.modify(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> delete(@PathVariable Long reviewId) {
        reviewService.delete(reviewId);
        return ResponseEntity.ok().body(true);
    }

}
