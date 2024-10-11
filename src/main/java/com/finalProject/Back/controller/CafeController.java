package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqCafeDto;
import com.finalProject.Back.dto.request.ReqGetCafeDto;
import com.finalProject.Back.entity.Cafe;
import com.finalProject.Back.service.CafeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok().body(cafeService.get(category));
    }

    @GetMapping("/cafe/get/{category}/{search}")
    public ResponseEntity<?> get(@PathVariable String category, @PathVariable String search){
        return ResponseEntity.ok().body(cafeService.getList(category, search));
    }
}
