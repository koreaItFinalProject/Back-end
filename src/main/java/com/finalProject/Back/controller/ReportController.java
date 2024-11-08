package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqReportDto;
import com.finalProject.Back.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;


    @PostMapping("/report")
    public ResponseEntity<?> report(@RequestBody ReqReportDto dto){
        return ResponseEntity.ok().body(reportService.save(dto));
    }

    @GetMapping("/report/get")
    public ResponseEntity<?> getReport(){
        return ResponseEntity.ok().body(reportService.getReport());
    }
}
