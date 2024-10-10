package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqCafeDto;
import com.finalProject.Back.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @PostMapping("/cafe/add")
    public ResponseEntity<?> add(@RequestBody ReqCafeDto dto){
        System.out.println(dto);
        return ResponseEntity.ok().body(cafeService.addCafe(dto));
    }
}
