package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqReportDto;
import com.finalProject.Back.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/report/{type}/{content}")
    public ResponseEntity<?> deleteReport(@PathVariable String type, @PathVariable String content){
        return ResponseEntity.ok().body(reportService.deleteReport(type, content));
    }
}
