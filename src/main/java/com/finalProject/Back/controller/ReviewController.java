package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqReviewDto;
import com.finalProject.Back.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/cafe/review")
    public ResponseEntity<?> write(@RequestBody ReqReviewDto.ReqWriteReviewDto dto) {
        reviewService.write(dto);
        return ResponseEntity.ok().body(true);
    }
}
